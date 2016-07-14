using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using RDotNet;
using System.IO;

namespace SampleApp_RDotNet_VS2012
{
    class RApplication
    {

        private string PIServerName = string.Empty;
        private string Interval = string.Empty;
        private string Mode = string.Empty;
        private string DataAccessMethod = string.Empty;
        private REngine engine = null;
        private readonly DateTime UnixEpoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
        private string RFunction = String.Empty;
        private int NumberTags = -1;
        public double ValueToBeShown = -1;
        public int ProgramCurrentStep = -1;
        private string MyTagName1 = string.Empty;
        private string MyTagName2 = string.Empty;
        private string MyTagName3 = string.Empty;
        private string MyTagName4 = string.Empty;
        private string MyTagName5 = string.Empty;
        private PIValues myPIValues1 = null;
        private PIValues myPIValues2 = null;
        private PIValues myPIValues3 = null;
        private PIValues myPIValues4 = null;
        private PIValues myPIValues5 = null;
        private PIData_PIAFSDK myAFSDK_App;
        private PIData_PIWS myPIWS_App;
        private PIData_PIWA myPIWA_App;

        public void SetDataAccessMethod(string dataAccessMethod)
        {
            DataAccessMethod=dataAccessMethod;
        }

        public string GetDataAccessMethod()
        {
            return (DataAccessMethod);
        }

        public void SetMode(string mode)
        {
            this.Mode = mode;
        }

        public string GetMode()
        {
            return (this.Mode);
        }


        public void SetInterval(string interval)
        {
            this.Interval = interval;
        }

        public string GetPIServerName()
        {
            return (PIServerName);
        }

        public void SetNumTags(int numtags)
        {
            NumberTags = numtags;
        }

        public int GetNumTags()
        {
            return (NumberTags);
        }


        public string GetRFunction()
        {
            return (RFunction);
        }

        public void SetRFunction(string rfunction_selected)
        {
            RFunction = rfunction_selected;
        }

        public RApplication()
        {
            DataAccessMethod = "PIAFSDK";
            this.InicializeRNet();
            this.Inicialize();

            
        }

        public void Inicialize()
        {
            this.Disconnect();
            if (DataAccessMethod == "PIAFSDK")
            {
                myAFSDK_App = new PIData_PIAFSDK();
            }
            if (DataAccessMethod == "PIWS")
            {
                myPIWS_App = new PIData_PIWS();
            }
            if (DataAccessMethod == "PIWA")
            {
                myPIWA_App = new PIData_PIWA();
            }
        }


        private void InicializeRNet()
        {
            //Create R.NET instance
            string rhome = System.Environment.GetEnvironmentVariable("R_HOME");
            engine = REngine.CreateInstance("RDotNet");
            engine.Initialize();

            //Copy the Function.R file from the source code package to the folder C:\Program Files\R\R-2.15.3\library\
            string rfilepath = @"C:\\Program Files\\R\\R-2.15.3\\library\\Functions.R";
            engine.Evaluate("source(\"" + rfilepath + "\")");
        }

        public string GetVersion()
        {
            if (DataAccessMethod == "PIAFSDK")
            {
                return (myAFSDK_App.GetAFSDKversion());
            }
            if (DataAccessMethod == "PIWS")
            {
                return (myPIWS_App.GetPIWSversion());
            }
            if (DataAccessMethod == "PIWA")
            {
                return (myPIWA_App.GetPIWAversion());
            }
            return string.Empty;
        }

        public int ConnectToPIServer(string piservername, int def)
        {
            this.PIServerName = piservername;
            if (DataAccessMethod == "PIAFSDK")
            {
                return (myAFSDK_App.ConnectToPIServer(piservername, def));
            }
            if (DataAccessMethod == "PIWS")
            {
                return (myPIWS_App.ConnectToPIServer(piservername, def));
            }
            if (DataAccessMethod == "PIWA")
            {
                return (myPIWA_App.ConnectToPIServer(piservername, def));
            }
            return 5;
        }

        public string[] GetPIServersList()
        {
            if (DataAccessMethod == "PIAFSDK")
            {
                return (myAFSDK_App.GetPIServersList());
            }
            if (DataAccessMethod == "PIWA")
            {
                string[] t = myPIWA_App.GetPIServersList();
                return (t);
            }
            if (DataAccessMethod == "PIWS")
            {
                return (myPIWS_App.GetPIServersList());
            }
            return null;    
        }


        public int GetPIData(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5, string starttime, string endtime, string interval)
        {
            MyTagName1 = string.Empty;
            MyTagName2 = string.Empty;
            MyTagName3 = string.Empty;
            MyTagName4 = string.Empty;
            MyTagName5 = string.Empty;
            myPIValues1 = null;
            myPIValues2 = null;
            myPIValues3 = null;
            myPIValues4 = null;
            myPIValues5 = null;

            int return_validatetagnames = ValidateTagNames(pitag1, pitag2, pitag3, pitag4, pitag5);
            if (return_validatetagnames == 1)
            {
                return 1;
            }

            int return_transferfrompi = TransferDataFromPI(pitag1, pitag2, pitag3, pitag4, pitag5, starttime, endtime, interval);
            if (return_transferfrompi == 2)
            {
                return 2;
            }

            return 0;

        }

        private int ValidateTagNames(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5)
        {
            if (DataAccessMethod == "PIAFSDK")
            {
                return myAFSDK_App.ValidateTagNames(pitag1, pitag2, pitag3, pitag4, pitag5, this.GetNumTags());
            }
            if (DataAccessMethod == "PIWS")
            {
                return myPIWS_App.ValidateTagNames(pitag1, pitag2, pitag3, pitag4, pitag5, this.GetNumTags());
            }
            if (DataAccessMethod == "PIWA")
            {
                return myPIWA_App.ValidateTagNames(pitag1, pitag2, pitag3, pitag4, pitag5, this.GetNumTags());
            }
            return 5;
        }

        private int TransferDataFromPI(string pitag1, string pitag2, string pitag3, string pitag4, string pitag5, string starttime, string endtime, string interval)
        {
            if (this.Mode == "Recorded")
            {
                try
                {

                    myPIValues1 = GetRecordedValues(1, starttime, endtime);
                    if (GetNumTags() > 1)
                    {
                        myPIValues2 = GetRecordedValues(2, starttime, endtime);
                    }
                    if (GetNumTags() > 2)
                    {
                        myPIValues3 = GetRecordedValues(3, starttime, endtime);
                    }
                    if (GetNumTags() > 3)
                    {
                        myPIValues4 = GetRecordedValues(4, starttime, endtime);
                    }
                    if (GetNumTags() > 4)
                    {
                        myPIValues5 = GetRecordedValues(5, starttime, endtime);
                    }
                }
                catch
                {
                    return 2;
                }
            }
            if (this.Mode == "Interpolated")
            {
                try
                {
                    myPIValues1 = GetInterpolatedValues(1, starttime, endtime, interval);
                    if (GetNumTags() > 1)
                    {
                        myPIValues2 = GetInterpolatedValues(2, starttime, endtime, interval);
                    }
                    if (GetNumTags() > 2)
                    {
                        myPIValues3 = GetInterpolatedValues(3, starttime, endtime, interval);
                    }
                    if (GetNumTags() > 3)
                    {
                        myPIValues4 = GetInterpolatedValues(4, starttime, endtime, interval);
                    }
                    if (GetNumTags() > 4)
                    {
                        myPIValues5 = GetInterpolatedValues(5, starttime, endtime, interval);
                    }
                }
                catch
                {
                    return 2;
                }

            }
            return 0;
        }

        private  PIValues GetRecordedValues(int i, string starttime, string endtime)
        {
            if (DataAccessMethod == "PIAFSDK")
            {
                return myAFSDK_App.GetRecordedValues(i, starttime, endtime);
            }
            if (DataAccessMethod == "PIWS")
            {
                return myPIWS_App.GetRecordedValues(i, starttime, endtime);
            }
            if (DataAccessMethod == "PIWA")
            {
                return myPIWA_App.GetRecordedValues(i, starttime, endtime);
            }
            return null;
        }

        private PIValues GetInterpolatedValues(int i, string starttime, string endtime, string interval)
        {
            if (DataAccessMethod == "PIAFSDK")
            {
                return myAFSDK_App.GetInterpolatedValues(i, starttime, endtime, interval);
            }
            if (DataAccessMethod == "PIWS")
            {
                return myPIWS_App.GetInterpolatedValues(i, starttime, endtime, interval);
            }
            if (DataAccessMethod == "PIWA")
            {
                return myPIWA_App.GetInterpolatedValues(i, starttime, endtime, interval);
            }
            return null;
        }

        private double GetUTCFormat(DateTime DateFormat)
        {
            double UtcFormat = 1;
            TimeSpan ts = DateFormat - UnixEpoch;
            UtcFormat = (ts.TotalMilliseconds) / 1000;
            return UtcFormat;

        }


        private double[] ConvertTSToDoubleArray(PIValues myPIValues)
        {
            int i = 1;
            int ii = 0;
            double[] MySourceTagArrayTSUTC = new double[myPIValues.Count];

            while (i <= myPIValues.Count)
            {
                try
                {
                    MySourceTagArrayTSUTC[ii] = this.GetUTCFormat(myPIValues[i].Timestamp);
                    ii++;
                }
                catch { }
                i++;
            }
            return (MySourceTagArrayTSUTC);
        }


        private double[] ConvertValuesToDoubleArray(PIValues myPIValues)
        {
            int i = 1;
            int ii = 0;
            double[] MySourceTagArrayValues = new double[myPIValues.Count];

            while (i <= myPIValues.Count)
            {
                try
                {
                    MySourceTagArrayValues[ii] = Convert.ToDouble(myPIValues[i].Value);
                    ii++;
                }
                catch { }
                i++;
            }
            return (MySourceTagArrayValues);
        }



        private void TransferDataToR()
        {

            double[] myPIValues1Values = ConvertValuesToDoubleArray(myPIValues1);
            double[] myPIValues1TS = ConvertTSToDoubleArray(myPIValues1);
            NumericVector tag1val = engine.CreateNumericVector(myPIValues1Values);
            engine.SetSymbol("tag1val", tag1val);
            NumericVector tag1tsd = engine.CreateNumericVector(myPIValues1TS);
            engine.SetSymbol("tag1tsd", tag1tsd);
            engine.Evaluate("tag1ts<-as.POSIXct(tag1tsd, origin='1970-01-01')");
            engine.Evaluate("tag1<- data.frame(tag1ts,tag1val)");


            if (this.GetNumTags() >= 2)
            {

                double[] myPIValues2Values = ConvertValuesToDoubleArray(myPIValues2);
                double[] myPIValues2TS = ConvertTSToDoubleArray(myPIValues2);
                NumericVector tag2val = engine.CreateNumericVector(myPIValues2Values);
                engine.SetSymbol("tag2val", tag2val);
                NumericVector tag2tsd = engine.CreateNumericVector(myPIValues2TS);
                engine.SetSymbol("tag2tsd", tag2tsd);
                engine.Evaluate("tag2ts<-as.POSIXct(tag2tsd, origin='1970-01-01')");
                engine.Evaluate("tag2<- data.frame(tag2ts,tag2val)");
            }

            if (this.GetNumTags() >= 3)
            {

                double[] myPIValues3Values = ConvertValuesToDoubleArray(myPIValues3);
                double[] myPIValues3TS = ConvertTSToDoubleArray(myPIValues3);
                NumericVector tag3val = engine.CreateNumericVector(myPIValues3Values);
                engine.SetSymbol("tag3val", tag3val);
                NumericVector tag3tsd = engine.CreateNumericVector(myPIValues3TS);
                engine.SetSymbol("tag3tsd", tag3tsd);
                engine.Evaluate("tag3ts<-as.POSIXct(tag3tsd, origin='1970-01-01')");
                engine.Evaluate("tag3<- data.frame(tag3ts,tag3val)");
            }

            if (this.GetNumTags() >= 4)
            {

                double[] myPIValues4Values = ConvertValuesToDoubleArray(myPIValues4);
                double[] myPIValues4TS = ConvertTSToDoubleArray(myPIValues4);
                NumericVector tag4val = engine.CreateNumericVector(myPIValues4Values);
                engine.SetSymbol("tag4val", tag4val);
                NumericVector tag4tsd = engine.CreateNumericVector(myPIValues4TS);
                engine.SetSymbol("tag4tsd", tag4tsd);
                engine.Evaluate("tag4ts<-as.POSIXct(tag4tsd, origin='1970-01-01')");
                engine.Evaluate("tag4<- data.frame(tag4ts,tag4val)");
            }

            if (this.GetNumTags() >= 5)
            {

                double[] myPIValues5Values = ConvertValuesToDoubleArray(myPIValues5);
                double[] myPIValues5TS = ConvertTSToDoubleArray(myPIValues5);
                NumericVector tag5val = engine.CreateNumericVector(myPIValues5Values);
                engine.SetSymbol("tag5val", tag5val);
                NumericVector tag5tsd = engine.CreateNumericVector(myPIValues5TS);
                engine.SetSymbol("tag5tsd", tag5tsd);
                engine.Evaluate("tag5ts<-as.POSIXct(tag5tsd, origin='1970-01-01')");
                engine.Evaluate("tag5<- data.frame(tag5ts,tag5val)");
            }
        }
        
        public void GenerateGhaphic(string par1, string par2, string par3, string par4)
        {
            TransferDataToR();

            if (this.RFunction == "PI Histogram")
            {

                string tag1name = "\"" + MyTagName1 + "\"";
                engine.Evaluate("PI_Histogram(tag1val," + par1 + "," + tag1name + "," + par2 + "," + par3 + ")");
            }

            if (this.RFunction == "PI Density Plot")
            {
                string mpar1 = "\"" + par1 + "\"";
                string mpar2 = "\"" + par2 + "\"";
                string mpar3 = "\"" + par3 + "\"";
                string tag1name = "\"" + MyTagName1 + "\"";
                engine.Evaluate("PI_Density_Plot(tag1$tag1val," + mpar1 + "," + mpar2 + "," + mpar3 + "," + par4 + "," + tag1name + ")");
            }

            if (this.RFunction == "PI Density Compare for two tags")
            {
                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                engine.Evaluate("PI_Density_Compare_TwoTags(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
            }

            if (this.RFunction == "PI Density Compare for three tags")
            {
                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                string tag3name = "\"" + MyTagName1 + "\"";
                engine.Evaluate("PI_Density_Compare_ThreeTags(tag1$tag1val,tag2$tag2val,tag3$tag3val," + tag1name + "," + tag2name + "," + tag3name + ")");
            }
            
            if (this.RFunction == "PI Box Plot")
            {
                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                engine.Evaluate("PI_Box_Plot(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + "," + par1 + "," + par2 + "," + par3 + ")");
            }
            if (this.RFunction == "PI Regular Correlation")
            {

                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                engine.Evaluate("PI_Regular_Correlation(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
                NumericVector ResultsShown = engine.Evaluate("cor(tag1$tag1val,tag2$tag2val)").AsNumeric();
                ValueToBeShown = ResultsShown[0];
            }

            if (this.RFunction == "PI Smooth Scatter")
            {

                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                engine.Evaluate("PI_Smooth_Scatter(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
            }

            if (this.RFunction == "PI Multi-Correlation for three tags")
            {
                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                string tag3name = "\"" + MyTagName3 + "\"";
                engine.Evaluate("impact3<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val)");
                engine.Evaluate("tn3<-c(" + tag1name + "," + tag2name + "," + tag3name + ")");
                engine.Evaluate("PI_Multi_Correlation3(impact3,tn3)");
            }

            if (this.RFunction == "PI Multi-Correlation for four tags")
            {
                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                string tag3name = "\"" + MyTagName3 + "\"";
                string tag4name = "\"" + MyTagName4 + "\"";
                engine.Evaluate("impact4<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val)");
                engine.Evaluate("tn4<-c(" + tag1name + "," + tag2name + "," + tag3name + "," + tag4name + ")");
                engine.Evaluate("PI_Multi_Correlation4(impact4,tn4)");
            }

            if (this.RFunction == "PI Multi-Correlation for five tags")
            {
                string tag1name = "\"" + MyTagName1 + "\"";
                string tag2name = "\"" + MyTagName2 + "\"";
                string tag3name = "\"" + MyTagName3 + "\"";
                string tag4name = "\"" + MyTagName4 + "\"";
                string tag5name = "\"" + MyTagName5 + "\"";
                engine.Evaluate("impact5<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val,tag5$tag5val)");
                engine.Evaluate("tn5<-c(" + tag1name + "," + tag2name + "," + tag3name + "," + tag4name + "," + tag5name + ")");
                engine.Evaluate("PI_Multi_Correlation5(impact5,tn5)");
            }
        }

        private int CountAFValues(PIValues myPIValues)
        {
            try
            {
                return (myPIValues.Count);
            }
            catch
            {
                return 0;
            }

        }

        public int IntCountAFValues(int n)
        {
            if (n == 1)
            {
                return (CountAFValues(myPIValues1));
            }
            if (n == 2)
            {
                return (CountAFValues(myPIValues2));
            }
            if (n == 3)
            {
                return (CountAFValues(myPIValues3));
            }
            if (n == 4)
            {
                return (CountAFValues(myPIValues4));
            }
            if (n == 5)
            {
                return (CountAFValues(myPIValues5));
            }
            return -1;
        }

        private void Disconnect()
        {
            if (myAFSDK_App != null)
            {
                myAFSDK_App.Disconnect();
                myAFSDK_App = null;
            }
            if (myPIWS_App != null)
            {
                myPIWS_App.Disconnect();
                myPIWS_App = null;
            }
            if (myPIWA_App != null)
            {
                myPIWA_App.Disconnect();
                myPIWA_App = null;
            }

        }

    }
}