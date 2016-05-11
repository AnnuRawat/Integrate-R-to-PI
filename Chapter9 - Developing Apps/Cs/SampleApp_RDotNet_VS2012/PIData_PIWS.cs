using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SampleApp_RDotNet_VS2012
{
    public class PIData_PIWS
    {

        private string PIServerName = string.Empty;
        private string MyTagName1 = string.Empty;
        private string MyTagName2 = string.Empty;
        private string MyTagName3 = string.Empty;
        private string MyTagName4 = string.Empty;
        private string MyTagName5 = string.Empty;
        private PIWSTimeSeriesRef.PITimeSeriesClient ptsc;
        private string[] paths;

        public PIData_PIWS()
        {
            ptsc = new PIWSTimeSeriesRef.PITimeSeriesClient();
            ptsc.Open();
        }


        public string GetPIWSversion()
        {
            return ptsc.GetProductVersion();
        }





        public int ConnectToPIServer(string piservername, int def)
        {


            string path = @"pi\\" + piservername + @"\sinusoid";
            var results = ptsc.GetPISnapshotData(new string[] { path });

            if (results[0].Error == 0)
            {
                PIServerName = piservername;
                return 1;
            }
            else
            {
                return 0;
            }
        }

        public string[] GetPIServersList()
        {
            return (new string[] { "Type the PI Server here!" });
        }


        public int ValidateTagNames(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5, int NumTags)
        {
            string path = @"pi\\" + PIServerName + @"\sinusoid";
            paths = null;
            paths = new string[NumTags];

            paths[0] = @"pi\\" + PIServerName + @"\" + pitag1;
            MyTagName1 = pitag1;
            if (NumTags > 1)
            {
                paths[1] = @"pi\\" + PIServerName + @"\" + pitag2;
                MyTagName2 = pitag2;
            }
            if (NumTags > 2)
            {
                paths[2] = @"pi\\" + PIServerName + @"\" + pitag3;
                MyTagName3 = pitag3;
            }
            if (NumTags > 3)
            {
                paths[3] = @"pi\\" + PIServerName + @"\" + pitag4;
                MyTagName4 = pitag4;
            }
            if (NumTags > 4)
            {
                paths[4] = @"pi\\" + PIServerName + @"\" + pitag5;
                MyTagName5 = pitag5;
            }

            var results = ptsc.GetPISnapshotData(paths);
            int detection_error = 0;

            if (results[0].Error != 0)
            {
                detection_error = 1;
            }
           
            if (NumTags > 1)
            {
                if (results[1].Error != 0)
                {
                    detection_error = 1;
                }
               
            }


            if (NumTags > 2)
            {
                if (results[2].Error != 0)
                {
                    detection_error = 1;
                }

            }

            if (NumTags > 3)
            {
                if (results[3].Error != 0)
                {
                    detection_error = 1;
                }

            }

            if (NumTags > 4)
            {
                if (results[4].Error != 0)
                {
                    detection_error = 1;
                }

            }

            return detection_error;
        }


        public PIValues GetRecordedValues(int i, string starttime, string endtime)
        {
            PIWSTimeSeriesRef.TimeRange timeRange = new PIWSTimeSeriesRef.TimeRange();
            timeRange.Start = starttime;
            timeRange.End = endtime;
            PIWSTimeSeriesRef.PIArcManner manner = new PIWSTimeSeriesRef.PIArcManner();
            manner.Boundaries = PIWSTimeSeriesRef.PIArcMannerBoundaries.Inside;
            manner.RetrievalType = PIWSTimeSeriesRef.PIArcMannerRetrievalType.Compressed;
            PIWSTimeSeriesRef.PIArcDataRequest request = new PIWSTimeSeriesRef.PIArcDataRequest();
            request.PIArcManner = manner;
            request.TimeRange = timeRange;       

            if (i==1)
            {
                request.Path = paths[0];
            }
            else if (i==2)
            {
                request.Path = paths[1];
            }
            else if (i==3)
            {
                request.Path = paths[2];
            }
            else if (i==4)
            {
                request.Path = paths[3];
            }
            else
            {
                request.Path = paths[4];
            }
            List<PIWSTimeSeriesRef.PIArcDataRequest> requests = new List<PIWSTimeSeriesRef.PIArcDataRequest>();
            requests.Add(request);
            PIWSTimeSeriesRef.PIArcDataRequest[] DataRequestsArray = requests.ToArray();
            PIWSTimeSeriesRef.TimeSeries[] MyTimeSeries = ptsc.GetPIArchiveData(DataRequestsArray);
            List<PIWSTimeSeriesRef.TimeSeries> results  = MyTimeSeries.ToList();
           

            return (ConvertToPIValues(results[0]));
            
        }

        public PIValues GetInterpolatedValues(int i, string starttime, string endtime, string interval)
        {
            PIWSTimeSeriesRef.TimeRange timeRange = new PIWSTimeSeriesRef.TimeRange();
            timeRange.Start = starttime;
            timeRange.End = endtime;
          
            PIWSTimeSeriesRef.PIArcManner manner = new PIWSTimeSeriesRef.PIArcManner();
            manner.Boundaries = PIWSTimeSeriesRef.PIArcMannerBoundaries.Inside;
            manner.RetrievalType = PIWSTimeSeriesRef.PIArcMannerRetrievalType.Interpolated;
            manner.NumValues = System.Convert.ToInt16(interval);
            PIWSTimeSeriesRef.PIArcDataRequest request = new PIWSTimeSeriesRef.PIArcDataRequest();
            request.PIArcManner = manner;
            request.TimeRange = timeRange;
          

            if (i == 1)
            {
                request.Path = paths[0];
            }
            else if (i == 2)
            {
                request.Path = paths[1];
            }
            else if (i == 3)
            {
                request.Path = paths[2];
            }
            else if (i == 4)
            {
                request.Path = paths[3];
            }
            else
            {
                request.Path = paths[4];
            }
            List<PIWSTimeSeriesRef.PIArcDataRequest> requests = new List<PIWSTimeSeriesRef.PIArcDataRequest>();
            requests.Add(request);
            List<PIWSTimeSeriesRef.TimeSeries> results = ptsc.GetPIArchiveData(requests.ToArray()).ToList();


            return (ConvertToPIValues(results[0]));
        }

        private PIValues ConvertToPIValues(PIWSTimeSeriesRef.TimeSeries result)
        {
            PIValues myPIValues = new PIValues();
            foreach (PIWSTimeSeriesRef.TimedValue value in result.TimedValues)
            {
                myPIValues.Add(new PIValue(value.Value, (DateTime)value.Time));
            }
            return myPIValues;
        }

        public void Disconnect()
        {
            if (ptsc != null)
            {
                ptsc.Close();
            }
        }

    }
}
