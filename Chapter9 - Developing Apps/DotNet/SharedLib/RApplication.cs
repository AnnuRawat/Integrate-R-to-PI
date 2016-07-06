using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using RDotNet;
using RDotNet.Graphics;
using System.IO;
using System.Diagnostics;



namespace SharedLib
{
    public class RApplication
    {
        private REngine engine = null;
        private readonly DateTime unixEpoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);


        public double ValueToBeShown = -1;
        public int ProgramCurrentStep = -1;
        private string[] piPointsName = null;
        private PIValuesList piValuesList = null;
        private IPIDeveloperTech piDevWrapper = null;

        public string DataAccessMethod { get; set; }
        public string Mode { get; set; }
        public int NumTags { get; set; }
        public string RFunction { get; set; }

        public string Interval { get; set; }

        public string GetPIServerName()
        {
            return piDevWrapper.GetServerName();
        }



        public RApplication(REngine rengine)
        {
            engine = rengine;
            DataAccessMethod = "PIAFSDK";
            this.InicializeRNet();
            this.Inicialize();


        }

        public void Inicialize()
        {
            this.Disconnect();
            if (DataAccessMethod == "PIAFSDK")
            {
                piDevWrapper = new PIAFSDK_Wrapper();
            }
            if (DataAccessMethod == "PIWA")
            {

                piDevWrapper = new PIWebAPI_Wrapper();
            }
        }


        private void InicializeRNet()
        {
            string rFilePath = @"C:\\Program Files\\R\\R-3.3.1\\library\\Functions.R";
            engine.Evaluate("source(\"" + rFilePath + "\")");


        }

        public string GetVersion()
        {
            return piDevWrapper.GetVersion();
        }



        public bool Connect(string piDataArchive = null)
        {
            return piDevWrapper.Connect(piDataArchive);
        }

        public string[] GetPIDataArchiveNames()
        {
            return piDevWrapper.GetPIDataArchiveNamesList();

        }


        public int GetPIData(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5, string starttime, string endtime, string interval)
        {
            List<string> piPointNames = new List<string>();
            piPointNames.Add(pitag1);
            if (NumTags > 1)
                piPointNames.Add(pitag2);
            if (NumTags > 2)
                piPointNames.Add(pitag3);
            if (NumTags > 3)
                piPointNames.Add(pitag4);
            if (NumTags > 4)
                piPointNames.Add(pitag5);

            bool tagsAreValid = ValidateTagNames(piPointNames.ToArray());
            if (tagsAreValid == false)
            {
                return 1;
            }

            bool transferSuccess = TransferDataFromPI(piPointNames.ToArray(), starttime, endtime, interval);
            if (transferSuccess == false)
            {
                return 2;
            }

            return 0;

        }

        private bool ValidateTagNames(string[] piPoints)
        {
            return piDevWrapper.ValidateTagNames(piPoints);
        }

        private bool TransferDataFromPI(string[] tagsList, string starttime, string endtime, string interval)
        {
            if (this.Mode == "Recorded")
            {
                try
                {
                    piValuesList = piDevWrapper.GetRecordedValues(starttime, endtime);
                }
                catch (Exception ex)
                {
                    Debug.WriteLine(ex.Message);
                    return false;
                }
            }
            if (this.Mode == "Interpolated")
            {
                try
                {
                    piValuesList = piDevWrapper.GetInterpolatedValues(starttime, endtime, interval);
                }
                catch
                {
                    return false;
                }

            }
            return true;
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



        private void TransferDataToR()
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

        }

        public void GenerateGhaphic(string par1, string par2, string par3, string par4)
        {
            TransferDataToR();

            string tag1name = (piValuesList.Count > 0) ? "\"" + piValuesList[0].PIPointName + "\"" : string.Empty;
            string tag2name = (piValuesList.Count > 1) ? "\"" + piValuesList[1].PIPointName + "\"" : string.Empty;
            string tag3name = (piValuesList.Count > 2) ? "\"" + piValuesList[2].PIPointName + "\"" : string.Empty;
            string tag4name = (piValuesList.Count > 3) ? "\"" + piValuesList[3].PIPointName + "\"" : string.Empty;
            string tag5name = (piValuesList.Count > 4) ? "\"" + piValuesList[4].PIPointName + "\"" : string.Empty;
            string mpar1 = "\"" + par1 + "\"";
            string mpar2 = "\"" + par2 + "\"";
            string mpar3 = "\"" + par3 + "\"";

            if (this.RFunction == "PI Histogram")
            {
                //string[] statements = new string[1];
                //statements[0] = "PI_Histogram(tag1val," + par1 + "," + tag1name + "," + par2 + "," + par3 + ")";
                //return statements;
                engine.Evaluate("PI_Histogram(tag1val," + par1 + "," + tag1name + "," + par2 + "," + par3 + ")");
            }


            if (this.RFunction == "PI Density Plot")
            {
                engine.Evaluate("PI_Density_Plot(tag1$tag1val," + mpar1 + "," + mpar2 + "," + mpar3 + "," + par4 + "," + tag1name + ")");
            }

            if (this.RFunction == "PI Density Compare for two tags")
            {
                engine.Evaluate("PI_Density_Compare_TwoTags(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
            }

            if (this.RFunction == "PI Density Compare for three tags")
            {
                engine.Evaluate("PI_Density_Compare_ThreeTags(tag1$tag1val,tag2$tag2val,tag3$tag3val," + tag1name + "," + tag2name + "," + tag3name + ")");
            }

            if (this.RFunction == "PI Box Plot")
            {
                engine.Evaluate("PI_Box_Plot(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + "," + par1 + "," + par2 + "," + par3 + ")");
            }
            if (this.RFunction == "PI Regular Correlation")
            {
                engine.Evaluate("PI_Regular_Correlation(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
                NumericVector ResultsShown = engine.Evaluate("cor(tag1$tag1val,tag2$tag2val)").AsNumeric();
                ValueToBeShown = ResultsShown[0];
            }

            if (this.RFunction == "PI Smooth Scatter")
            {
                engine.Evaluate("PI_Smooth_Scatter(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
            }

            if (this.RFunction == "PI Multi-Correlation for three tags")
            {
                engine.Evaluate("impact3<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val)");
                engine.Evaluate("tn3<-c(" + tag1name + "," + tag2name + "," + tag3name + ")");
                engine.Evaluate("PI_Multi_Correlation3(impact3,tn3)");
            }
            if (this.RFunction == "PI Multi-Correlation for four tags")
            {
                engine.Evaluate("impact4<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val)");
                engine.Evaluate("tn4<-c(" + tag1name + "," + tag2name + "," + tag3name + "," + tag4name + ")");
                engine.Evaluate("PI_Multi_Correlation4(impact4,tn4)");
            }

            if (this.RFunction == "PI Multi-Correlation for five tags")
            {
                engine.Evaluate("impact5<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val,tag5$tag5val)");
                engine.Evaluate("tn5<-c(" + tag1name + "," + tag2name + "," + tag3name + "," + tag4name + "," + tag5name + ")");
                engine.Evaluate("PI_Multi_Correlation5(impact5,tn5)");
            }



        }

        public int IntCountAFValues(int n)
        {
            return piValuesList[n - 1].Count;
        }

        private void Disconnect()
        {
            if (piDevWrapper != null)
            {
                piDevWrapper.Disconnect();
            }

        }

    }
}