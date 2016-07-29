using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using RDotNet;
using RDotNet.Graphics;
using System.IO;
using System.Diagnostics;
using OSIsoft.AF;
using OSIsoft.AF.Asset;
using OSIsoft.AF.Time;
using OSIsoft.AF.PI;
using System.Configuration;



namespace RMultWebService
{
    public class RApplication
    {

        private PIValuesList piValuesList = null;
        private readonly DateTime unixEpoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);

        public RApplication()
        {
            PISystems piSystems = new PISystems();
            string piSystemName = ConfigurationManager.AppSettings["piSystemName"];
            PISystem piSystem = piSystems[piSystemName];
        }

        public void GetPIData(string[] paths, string startTime, string endTime, string interval)
        {
            string[] correctPaths = paths.Select(m => m.Substring(3)).ToArray();
            AFKeyedResults<string, AFAttribute> results = AFAttribute.FindAttributesByPath(correctPaths, null);
            AFAttributeList attributeList = new AFAttributeList();
            foreach (AFAttribute attribute in results)
            {
                attributeList.Add(attribute);
            }

            AFTime start = new AFTime(startTime);
            AFTime end = new AFTime(endTime);
            AFTimeRange timeRange = new AFTimeRange(start, end);
            AFTimeSpan timeSpan = AFTimeSpan.Parse(interval);
            IEnumerable<AFValues> valueResults = attributeList.Data.InterpolatedValues(timeRange, timeSpan, string.Empty, false, new PIPagingConfiguration(PIPageType.TagCount, 100));
            piValuesList = new PIValuesList(valueResults);
        }

        public void GenerateGraphic(REngine engine)
        {
            for (int i = 1; i <= piValuesList.Count; i++)
            {

                double[] values = ConvertValuesToDoubleArray(piValuesList[i - 1]);
                double[] ts = ConvertTSToDoubleArray(piValuesList[i - 1]);
                NumericVector tagval = engine.CreateNumericVector(values);
                engine.SetSymbol("tag" + i.ToString() + "val", tagval);
                NumericVector tag_tsd = engine.CreateNumericVector(ts);
                engine.SetSymbol("tag" + i.ToString() + "tsd", tag_tsd);
                engine.Evaluate("tag" + i.ToString() + "ts<-as.POSIXct(tag" + i.ToString() + "tsd, origin='1970-01-01')");
                engine.Evaluate("tag" + i.ToString() + "<- data.frame(tag" + i.ToString() + "ts,tag" + i.ToString() + "val)");
            }
            int[] arrayHelper = Enumerable.Range(1, piValuesList.Count).ToArray();
            string[] tagnames = piValuesList.Select(m => "\"" + m.Name + "\"").ToArray();
            string impactString = string.Join(",", arrayHelper.Select(m => "tag" + m + "$tag" + m + "val"));
            string tnString = string.Join(",", tagnames);

            engine.Evaluate("impact<-data.frame(" + impactString + ")");
            engine.Evaluate("tn<-c(" + tnString + ")");
            engine.Evaluate("PI_Multi_Correlation(impact,tn)");
        }

        private double GetUTCFormat(DateTime DateFormat)
        {
            double UtcFormat = 1;
            TimeSpan ts = DateFormat - unixEpoch;
            UtcFormat = (ts.TotalMilliseconds) / 1000;
            return UtcFormat;

        }


        private double[] ConvertTSToDoubleArray(PIValues piValues)
        {
            return piValues.Select(val => GetUTCFormat(val.Timestamp)).ToArray();
        }


        private double[] ConvertValuesToDoubleArray(PIValues piValues)
        {
            return piValues.Select(val => Convert.ToDouble(val.Value)).ToArray();
        }








    }
}