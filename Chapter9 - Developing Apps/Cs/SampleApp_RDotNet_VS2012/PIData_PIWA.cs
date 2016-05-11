using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace SampleApp_RDotNet_VS2012
{
    public class PIData_PIWA
    {
        private string baseUrl = "https://marc-web-sql.marc.net/piwebapi/";
        private IDictionary<string, string> PIServersWebIdDic;
        private string PIServerName = string.Empty;
        private string MyTagName1 = string.Empty;
        private string MyTagName2 = string.Empty;
        private string MyTagName3 = string.Empty;
        private string MyTagName4 = string.Empty;
        private string MyTagName5 = string.Empty;
        private dynamic MyTag1 = null;
        private dynamic MyTag2 = null;
        private dynamic MyTag3 = null;
        private dynamic MyTag4 = null;
        private dynamic MyTag5 = null;

        public PIData_PIWA()
        {
            
        }


        public string GetPIWAversion()
        {
            string VersionUrl = baseUrl + "/version";
            dynamic VersionResult = MakeRequest(VersionUrl).Result;
            return (VersionResult.FullVersion.Value);
        }





        public int ConnectToPIServer(string piservername, int def)
        {
            if (PIServersWebIdDic == null)
            {
                GetPIServersListWebId();
            }

            try
            {

                if (def == 0)
                {

                }
                else
                {
                    string[] PIServersList = GetPIServersList();
                    piservername = PIServersList[0];

                }
                string PIPointsUrl = PIServersWebIdDic[piservername];
                //dynamic PIPointsWebAPI = MakeRequest(PIPointsUrl).Result;

                PIServerName = piservername;
                return 1;
            }
            catch
            {
                return 0;
            }     
        }

        private void GetPIServersListWebId()
        {
            string PIServersUrl = baseUrl + "dataservers";
            dynamic PIServersList = MakeRequest(PIServersUrl).Result;
            PIServersWebIdDic = null;
            PIServersWebIdDic = new Dictionary<string, string>();            

            for (int i = 0; i < PIServersList.Items.Count; i++)
            {
                PIServersWebIdDic.Add(PIServersList.Items[i].Name.Value, PIServersList.Items[i].Links.Points.Value);
            }
        }

        public string[] GetPIServersList()
        {
            GetPIServersListWebId();
            List<string> sPIServersList = new List<string>();
            foreach(var PIServer in PIServersWebIdDic)
            {
                sPIServersList.Add(PIServer.Key);
            }
            return sPIServersList.ToArray();
        }


        public int ValidateTagNames(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5,int NumTags)
        {
            
            try
            {
                string PIPointUrl1 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag1;
                MyTag1 = MakeRequest(PIPointUrl1).Result;
                Object Id = MyTag1.Id.Value;
                MyTagName1 = pitag1;
            

                if (NumTags > 1)
                {
                    string PIPointUrl2 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag2;
                    MyTag2 = MakeRequest(PIPointUrl2).Result;
                    Object Id2 = MyTag2.Id.Value;
                    MyTagName2 = pitag2;
                }
                if (NumTags > 2)
                {
                    string PIPointUrl3 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag3;
                    MyTag3 = MakeRequest(PIPointUrl3).Result;
                    Object Id3 = MyTag3.Id.Value;
                    MyTagName3 = pitag3;
                }
                if (NumTags > 3)
                {
                    string PIPointUrl4 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag4;
                    MyTag4 = MakeRequest(PIPointUrl4).Result;
                    Object Id4 = MyTag4.Id.Value;
                    MyTagName4 = pitag4;
                }
                if (NumTags > 4)
                {
                    string PIPointUrl5 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag5;
                    MyTag5 = MakeRequest(PIPointUrl5).Result;
                    Object Id5 = MyTag5.Id.Value;
                    MyTagName5 = pitag5;
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
            string PIPointRecData1Url;
            dynamic PIPointRecData;
            if (i==1)
            {
                PIPointRecData1Url = MyTag1.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
                PIPointRecData = MakeRequest(PIPointRecData1Url).Result;  
            }
            else if (i==2)
            {
                PIPointRecData1Url = MyTag2.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
                PIPointRecData = MakeRequest(PIPointRecData1Url).Result;   
            }
            else if (i==3)
            {
                PIPointRecData1Url = MyTag3.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
                PIPointRecData = MakeRequest(PIPointRecData1Url).Result;   
            }
            else if (i==4)
            {
                PIPointRecData1Url = MyTag4.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
                PIPointRecData = MakeRequest(PIPointRecData1Url).Result;    
            }
            else
            {
                PIPointRecData1Url = MyTag5.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
                PIPointRecData = MakeRequest(PIPointRecData1Url).Result;    
            }
            return (ConvertToPIValues(PIPointRecData));
           
            
        }

        public PIValues GetInterpolatedValues(int i, string starttime, string endtime, string interval)
        {
            string PIPointIntData1Url;
            dynamic PIPointIntData;
            if (i == 1)
            {
                PIPointIntData1Url = MyTag1.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
                PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
            }
            else if (i == 2)
            {
                PIPointIntData1Url = MyTag2.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
                PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
            }
            else if (i == 3)
            {
                PIPointIntData1Url = MyTag3.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
                PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
            }
            else if (i == 4)
            {
                PIPointIntData1Url = MyTag4.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
                PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
            }
            else
            {
                PIPointIntData1Url = MyTag5.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
                PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
            }
            return (ConvertToPIValues(PIPointIntData));
        }

        private PIValues ConvertToPIValues(dynamic RESTPIValues)
        {
            PIValues myPIValues = new PIValues();
            for (int i = 0; i < RESTPIValues.Items.Count; i++)
            {
                myPIValues.Add(new PIValue(RESTPIValues.Items[i].Value.Value, (DateTime)RESTPIValues.Items[i].Timestamp.Value));
            }

            return myPIValues;

        }

        private static async Task<dynamic> MakeRequest(string url)
        {
            WebRequest request = WebRequest.Create(url);
            WebResponse response = request.GetResponse();

            using (StreamReader sw = new StreamReader(response.GetResponseStream()))
            {
                using (JsonTextReader reader = new JsonTextReader(sw))
                {
                    return JObject.ReadFrom(reader);
                }
            }
        }

        public void Disconnect()
        {
        
        }

    }
}
