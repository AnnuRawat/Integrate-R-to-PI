using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace SharedLib
{
    public class PIWebAPI_Wrapper : IPIDeveloperTech
    {
        private string baseUrl = "https://marc-web-sql.marc.net/piwebapi/";
        private IDictionary<string, string> PIServersWebIdDic;
        private string piDataArchiveName = string.Empty;
        private string[] webIds = null;


        public PIWebAPI_Wrapper()
        {

        }

        public string GetVersion()
        {
            string url = baseUrl + "system/versions";
            dynamic result = MakeRequest(url).Result;
            return (result["OSIsoft.REST"].FullVersion.Value);
        }
        public bool Connect(string PIDataArchiveName = null)
        {
            piDataArchiveName = PIDataArchiveName;
            if (PIDataArchiveName == null)
            {
                return false;
            }
            try
            {
                string url = baseUrl + @"points?path=\\" + piDataArchiveName + @"\sinusoid";
                dynamic result = MakeRequest(url).Result;
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }
        public string[] GetPIDataArchiveNamesList()
        {
            string url = baseUrl + "dataservers";
            dynamic result = MakeRequest(url).Result;
            string[] piDataArchiveNames = new string[result.Items.Count];
            PIServersWebIdDic = new Dictionary<string, string>();

            for (int i = 0; i < result.Items.Count; i++)
            {
                piDataArchiveNames[i] = result.Items[i].Name;
            }
            return piDataArchiveNames;
        }

        private dynamic GenerateSingleBatch(string piPointName)
        {
            dynamic singleBatch = new JObject();
            singleBatch.Method = "GET";
            singleBatch.Resource = @"https://marc-web-sql.marc.net/piwebapi/points?path=\\" + piDataArchiveName + @"\" + piPointName;
            singleBatch.Headers = new JObject();
            singleBatch.Headers["Cache-Control"] = "no-cache";
            return singleBatch;
        }

        public bool ValidateTagNames(string[] piPoints)
        {
            dynamic postBatch = new JObject();
            webIds = new string[piPoints.Length];
            for (int i = 0; i < piPoints.Length; i++)
            {
                postBatch[i.ToString()] = GenerateSingleBatch(piPoints[i]);
            }
            dynamic piPointWebIds = SendBatchRequest(postBatch).Result;
            for (int i = 0; i < piPoints.Length; i++)
            {
    
                dynamic piPointInfo = piPointWebIds[i.ToString()];             
                int status = Convert.ToInt32(piPointInfo.Status.Value);
                if (status != 200)
                {
                    return false;
                }
                else
                {
                    webIds[i] = piPointInfo.Content.WebId;
                }
            }
            return true;
        }
        public PIValuesList GetRecordedValues(string startTime, string endTime)
        {  
            string webIdsString = string.Join("&", webIds.Select(w => "webId=" + w));
            string url = baseUrl + @"streamsets/recorded?starttime=" + startTime + "&endtime=" + endTime + "&" + webIdsString;
            dynamic result = MakeRequest(url).Result;
            return new PIValuesList(result);
        }
        public PIValuesList GetInterpolatedValues(string startTime, string endTime, string interval)
        {  
            string webIdsString = string.Join("&", webIds.Select(w => "webId=" + w));
            string url = baseUrl + @"streamsets/interpolated?starttime=" + startTime + "&endtime=" + endTime + "&interval=" + interval + "&" + webIdsString;
            dynamic result = MakeRequest(url).Result;
            return new PIValuesList(result);
        }


        public string GetServerName()
        {
            return piDataArchiveName;
        }








        //public int ValidateTagNames(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5, int NumTags)
        //{

        //    try
        //    {
        //        string PIPointUrl1 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag1;
        //        MyTag1 = MakeRequest(PIPointUrl1).Result;
        //        Object Id = MyTag1.Id.Value;
        //        MyTagName1 = pitag1;


        //        if (NumTags > 1)
        //        {
        //            string PIPointUrl2 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag2;
        //            MyTag2 = MakeRequest(PIPointUrl2).Result;
        //            Object Id2 = MyTag2.Id.Value;
        //            MyTagName2 = pitag2;
        //        }
        //        if (NumTags > 2)
        //        {
        //            string PIPointUrl3 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag3;
        //            MyTag3 = MakeRequest(PIPointUrl3).Result;
        //            Object Id3 = MyTag3.Id.Value;
        //            MyTagName3 = pitag3;
        //        }
        //        if (NumTags > 3)
        //        {
        //            string PIPointUrl4 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag4;
        //            MyTag4 = MakeRequest(PIPointUrl4).Result;
        //            Object Id4 = MyTag4.Id.Value;
        //            MyTagName4 = pitag4;
        //        }
        //        if (NumTags > 4)
        //        {
        //            string PIPointUrl5 = baseUrl + "points?path=\\\\" + PIServerName + "\\" + pitag5;
        //            MyTag5 = MakeRequest(PIPointUrl5).Result;
        //            Object Id5 = MyTag5.Id.Value;
        //            MyTagName5 = pitag5;
        //        }

        //    }
        //    catch
        //    {
        //        return 1;
        //    }

        //    return 0;

        //}

        //public PIValues GetRecordedValues(int i, string starttime, string endtime)
        //{
        //    string PIPointRecData1Url;
        //    dynamic PIPointRecData;
        //    if (i == 1)
        //    {
        //        PIPointRecData1Url = MyTag1.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
        //        PIPointRecData = MakeRequest(PIPointRecData1Url).Result;
        //    }
        //    else if (i == 2)
        //    {
        //        PIPointRecData1Url = MyTag2.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
        //        PIPointRecData = MakeRequest(PIPointRecData1Url).Result;
        //    }
        //    else if (i == 3)
        //    {
        //        PIPointRecData1Url = MyTag3.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
        //        PIPointRecData = MakeRequest(PIPointRecData1Url).Result;
        //    }
        //    else if (i == 4)
        //    {
        //        PIPointRecData1Url = MyTag4.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
        //        PIPointRecData = MakeRequest(PIPointRecData1Url).Result;
        //    }
        //    else
        //    {
        //        PIPointRecData1Url = MyTag5.Links["Recorded Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime;
        //        PIPointRecData = MakeRequest(PIPointRecData1Url).Result;
        //    }
        //    return (ConvertToPIValues(PIPointRecData));


        //}

        //public PIValues GetInterpolatedValues(int i, string starttime, string endtime, string interval)
        //{
        //    string PIPointIntData1Url;
        //    dynamic PIPointIntData;
        //    if (i == 1)
        //    {
        //        PIPointIntData1Url = MyTag1.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
        //        PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
        //    }
        //    else if (i == 2)
        //    {
        //        PIPointIntData1Url = MyTag2.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
        //        PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
        //    }
        //    else if (i == 3)
        //    {
        //        PIPointIntData1Url = MyTag3.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
        //        PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
        //    }
        //    else if (i == 4)
        //    {
        //        PIPointIntData1Url = MyTag4.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
        //        PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
        //    }
        //    else
        //    {
        //        PIPointIntData1Url = MyTag5.Links["Interpolated Data"].Value + "?starttime=" + starttime + "&endtime=" + endtime + "&interval=" + interval;
        //        PIPointIntData = MakeRequest(PIPointIntData1Url).Result;
        //    }
        //    return (ConvertToPIValues(PIPointIntData));
        //}

        //private PIValues ConvertToPIValues(dynamic RESTPIValues)
        //{
        //    PIValues myPIValues = new PIValues();
        //    for (int i = 0; i < RESTPIValues.Items.Count; i++)
        //    {
        //        myPIValues.Add(new PIValue(RESTPIValues.Items[i].Value.Value, (DateTime)RESTPIValues.Items[i].Timestamp.Value));
        //    }

        //    return myPIValues;

        //}

        private static async Task<dynamic> MakeRequest(string url)
        {
            WebRequest request = WebRequest.Create(url);
            request.Credentials = new NetworkCredential("marc.adm", "kk");
            WebResponse response = request.GetResponse();

            using (StreamReader sw = new StreamReader(response.GetResponseStream()))
            {
                using (JsonTextReader reader = new JsonTextReader(sw))
                {
                    return JObject.ReadFrom(reader);
                }
            }
        }

        private static async Task<dynamic> SendBatchRequest(dynamic postBatch)
        {
            string url = "https://marc-web-sql.marc.net/piwebapi/batch";




            WebRequest request = WebRequest.Create(url);


            request.Credentials = new NetworkCredential("marc.adm", "kk");


            ((HttpWebRequest)request).UserAgent = ".NET Framework Example Client";


            request.Method = "POST";
            request.ContentType = "application/json";


            byte[] byteArray = Encoding.UTF8.GetBytes(postBatch.ToString());
            request.ContentLength = byteArray.Length;
            Stream dataStream = request.GetRequestStream();
            dataStream.Write(byteArray, 0, byteArray.Length);
            dataStream.Close();


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
