using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OSIsoft.AF;
using OSIsoft.AF.Data;
using OSIsoft.AF.Asset;
using OSIsoft.AF.PI;
using OSIsoft.AF.Time;

namespace SampleApp_RDotNet_VS2012
{
    public class PIData_PIAFSDK
    {
        private PISystems MyPISystems = null;
        private PIServers MyPIServers = null;
        private PIServer MyPiServer = null;
        private PIPoint MyTag1 = null;
        private PIPoint MyTag2 = null;
        private PIPoint MyTag3 = null;
        private PIPoint MyTag4 = null;
        private PIPoint MyTag5 = null;
        private AFAttribute MyAtrbTag1 = null;
        private AFAttribute MyAtrbTag2 = null;
        private AFAttribute MyAtrbTag3 = null;
        private AFAttribute MyAtrbTag4 = null;
        private AFAttribute MyAtrbTag5 = null;
        private string PIServerName = string.Empty;

        public PIData_PIAFSDK()
        {
            MyPISystems = new PISystems();
            MyPIServers = new PIServers();
        }


        public string GetAFSDKversion()
        {
            return (MyPISystems.Version);
        }





        public int ConnectToPIServer(string piservername, int def)
        {
            try
            {

                if (def == 0)
                {
                    this.PIServerName = piservername;
                    MyPiServer = MyPIServers[piservername];
                }
                else
                {
                    this.PIServerName = "Default PI Server";
                    MyPiServer = MyPIServers.DefaultPIServer;
                    string test = MyPiServer.Name;

                }
                MyPiServer.Connect();
                this.PIServerName = MyPiServer.Name;
                return 1;
            }
            catch
            {
                return 0;
            }

        }

        public string[] GetPIServersList()
        {
            int i = 0;
            

            string[] sPIServersList = new string[MyPIServers.Count];
            foreach (PIServer piserver in MyPIServers)
            {
                sPIServersList[i] = piserver.Name;
                i++;
            }
            return (sPIServersList);
        }


        public int ValidateTagNames(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5,int NumTags)
        {
            try
            {
                MyTag1 = PIPoint.FindPIPoint(MyPiServer, pitag1);
                MyAtrbTag1 = AFAttribute.FindAttribute(@"\\" + PIServerName + @"\" + MyTag1.Name, null);
                if (NumTags > 1)
                {
                    MyTag2 = PIPoint.FindPIPoint(MyPiServer, pitag2);
                    MyAtrbTag2 = AFAttribute.FindAttribute(@"\\" + PIServerName + @"\" + MyTag2.Name, null);
                }
                if (NumTags > 2)
                {
                    MyTag3 = PIPoint.FindPIPoint(MyPiServer, pitag3);
                    MyAtrbTag3 = AFAttribute.FindAttribute(@"\\" + PIServerName + @"\" + MyTag3.Name, null);
                }
                if (NumTags > 3)
                {
                    MyTag4 = PIPoint.FindPIPoint(MyPiServer, pitag4);
                    MyAtrbTag4 = AFAttribute.FindAttribute(@"\\" + PIServerName + @"\" + MyTag4.Name, null);
                }
                if (NumTags > 4)
                {
                    MyTag5 = PIPoint.FindPIPoint(MyPiServer, pitag5);
                    MyAtrbTag5 = AFAttribute.FindAttribute(@"\\" + PIServerName + @"\" + MyTag5.Name, null);
                }

            }
            catch
            {
                return 1;
            }

            return 0;
        }

        public PIValues GetRecordedValues(int i, string starttime, string endtime)
        {
            AFTime startTime = new AFTime(starttime);
            AFTime endTime = new AFTime(endtime);
            AFTimeRange timeRange = new AFTimeRange(startTime, endTime);
            AFValues myAFValues;
            if (i==1)
            {
                myAFValues = MyAtrbTag1.Data.RecordedValues(timeRange, AFBoundaryType.Inside, null, null, false);
            }
            else if (i==2)
            {
                myAFValues = MyAtrbTag2.Data.RecordedValues(timeRange, AFBoundaryType.Inside, null, null, false);
            }
            else if (i==3)
            {
                myAFValues = MyAtrbTag3.Data.RecordedValues(timeRange, AFBoundaryType.Inside, null, null, false);
            }
            else if (i==4)
            {
                myAFValues = MyAtrbTag4.Data.RecordedValues(timeRange, AFBoundaryType.Inside, null, null, false);
            }
            else
            {
                myAFValues = MyAtrbTag5.Data.RecordedValues(timeRange, AFBoundaryType.Inside, null, null, false);
            }
            return (ConvertToPIValues(myAFValues));
        }

        public PIValues GetInterpolatedValues(int i, string starttime, string endtime, string interval)
        {
            AFTime startTime = new AFTime(starttime);
            AFTime endTime = new AFTime(endtime);
            AFTimeRange timeRange = new AFTimeRange(startTime, endTime);
            AFTimeSpan timeSpan = AFTimeSpan.Parse(interval);
            AFValues myAFValues;
            if (i == 1)
            {
                myAFValues = MyAtrbTag1.Data.InterpolatedValues(timeRange, timeSpan, null, null, false);
            }
            else if (i == 2)
            {
                myAFValues = MyAtrbTag2.Data.InterpolatedValues(timeRange, timeSpan, null, null, false);
            }
            else if (i == 3)
            {
                myAFValues = MyAtrbTag3.Data.InterpolatedValues(timeRange, timeSpan, null, null, false);
            }
            else if (i == 4)
            {
                myAFValues = MyAtrbTag4.Data.InterpolatedValues(timeRange, timeSpan, null, null, false);
            }
            else
            {
                myAFValues = MyAtrbTag5.Data.InterpolatedValues(timeRange, timeSpan, null, null, false);
            }
            return (ConvertToPIValues(myAFValues));
        }

        private PIValues ConvertToPIValues(AFValues myAFValues)
        {
            PIValues myPIValues = new PIValues();
            foreach (AFValue myAFValue in myAFValues)
            {
                myPIValues.Add(new PIValue(myAFValue.Value,myAFValue.Timestamp.LocalTime.ToLocalTime()));
            
            }
            return myPIValues;
        }

        public void Disconnect()
        {
            if (MyPiServer != null)
            {
                if (MyPiServer.ConnectionInfo.IsConnected)
                {
                    MyPiServer.Disconnect();
                }
            }
        }

    }
}
