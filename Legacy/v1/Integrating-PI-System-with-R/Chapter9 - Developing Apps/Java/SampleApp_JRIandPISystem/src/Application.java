

import java.util.List;
import java.util.*;


import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class Application {
	private boolean JavaGdWindowClose = true;
	private List<PIValue> myPIValues1 = null;
	private List<PIValue> myPIValues2 = null;
	private List<PIValue> myPIValues3 = null;
	private List<PIValue> myPIValues4 = null;
	private List<PIValue> myPIValues5 = null;
	private String MyTagName1 = "";
	private String MyTagName2 = "";
	private String MyTagName3 = "";
	private String MyTagName4 = "";
	private String MyTagName5 = "";
	private String PIServerName = null;
	private String Mode = null;
	private String RFunction = null;
	public int isConnected;
	private Rengine engine;
	private int NumberTags = -1;
	public double ValueToBeShown = -1;
	public int ProgramCurrentStep = -1;
	private PIData_PIJDBC myPIJDBC_App;
    private PIData_PIWS myPIWS_App;
    private PIData_PIWA myPIWA_App;
    private String DataAccessMethod="";

	public void SetMode(String mode) {
		this.Mode = mode;
	}

	public Application() {
        DataAccessMethod = "PIJDBC";
        this.InicializeRNet();
        this.Inicialize();
	}
	
    private void InicializeRNet()
    {
    
		System.out.println("Creating Rengine (with arguments)");
		// If not started with --vanilla, funny things may happen in this R
		// shell.
		String[] Rargs = { "--vanilla" };
		engine = new Rengine(Rargs, false, null);
		// System.out.println("Rengine created, waiting for R");
		// the engine creates R is a new thread, so we should wait until it's
		// ready
		if (!engine.waitForR()) {
			System.out.println("Cannot load R");
			return;
		}
		String rfilepath = "C:\\\\Program Files\\\\R\\\\R-2.15.1\\\\library\\\\Functions.R";
		engine.eval("source(\"" + rfilepath + "\")");

    }
	
	
    public void Inicialize()
    {
        this.Disconnect();
        if (DataAccessMethod == "PIJDBC")
        {
        	myPIJDBC_App = new PIData_PIJDBC();
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
	

	public String GetMode() {
		return (this.Mode);
	}
	
	public void SetDataAccessMethod(String dataAccessMethod)
    {
        DataAccessMethod=dataAccessMethod;
    }
	
	public String GetDataAccessMethod()
    {
        return (DataAccessMethod);
    }



	public String GetPIServerName() {
		return (PIServerName);
	}

	public void SetNumTags(int numtags) {
		NumberTags = numtags;
	}

	public int GetNumTags() {
		return (NumberTags);
	}

	public String GetRFunction() {
		return (RFunction);
	}

	public void SetRFunction(String rfunction_selected) {
		RFunction = rfunction_selected;
	}
	
    public String GetVersion()
    {
        if (DataAccessMethod == "PIJDBC")
        {
            return (myPIJDBC_App.GetPIJDBCversion());
        }
        if (DataAccessMethod == "PIWS")
        {
            return (myPIWS_App.GetPIWSversion());
        }
        if (DataAccessMethod == "PIWA")
        {
            return (myPIWA_App.GetPIWAversion());
        }
        return "";
    }

	public void Connect(String PIServerName) {
		if (DataAccessMethod == "PIJDBC")
        {
			isConnected = myPIJDBC_App.ConnectToPIServer(PIServerName);
		}
		  if (DataAccessMethod == "PIWS")
        {
			  isConnected = myPIWS_App.ConnectToPIServer(PIServerName);
        }
        if (DataAccessMethod == "PIWA")
        {
        	isConnected = myPIWA_App.ConnectToPIServer(PIServerName);
        }
	}
	

	private int ValidateTagNames(String pitag1, String pitag2, String pitag3,
			String pitag4, String pitag5)
	{
		  if (DataAccessMethod == "PIJDBC")
          {
				return (myPIJDBC_App.ValidateTagNames(pitag1, pitag2, pitag3, pitag4,pitag5, this.GetNumTags())); 
          }
		  if (DataAccessMethod == "PIWS")
          {
        	  return (myPIWS_App.ValidateTagNames(pitag1, pitag2, pitag3, pitag4,pitag5, this.GetNumTags())); 
          }
          if (DataAccessMethod == "PIWA")
          {
        	  return (myPIWA_App.ValidateTagNames(pitag1, pitag2, pitag3, pitag4,pitag5, this.GetNumTags())); 
          }
          return 5;
	}


	public int GetPIData(String pitag1, String pitag2, String pitag3,
			String pitag4, String pitag5, String starttime, String endtime,
			String interval) {

		int return_validatetagnames = ValidateTagNames(pitag1, pitag2, pitag3,
				pitag4, pitag5);
		if (return_validatetagnames == 1) {
			return 1;
		}

		int return_transferfrompi = TransferDataFromPI(pitag1, pitag2, pitag3,
				pitag4, pitag5, starttime, endtime, interval);
		if (return_transferfrompi == 2) {
			return 2;
		}

		return 0;
	}

	private int TransferDataFromPI(String pitag1, String pitag2, String pitag3,
			String pitag4, String pitag5, String starttime, String endtime,
			String interval) {
		if (this.Mode == "Recorded") {
			try {
				myPIValues1 = GetRecordedValues(1, starttime, endtime);

				if (GetNumTags() > 1) {
					myPIValues2 = GetRecordedValues(2, starttime, endtime);

				}
				if (GetNumTags() > 2) {
					myPIValues3 = GetRecordedValues(3, starttime, endtime);
				}
				if (GetNumTags() > 3) {
					myPIValues4 = GetRecordedValues(4, starttime, endtime);
				}
				if (GetNumTags() > 4) {
					myPIValues5 = GetRecordedValues(5, starttime, endtime);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return 2;
			}
		}

		if (this.Mode == "Interpolated") {
			try {
				myPIValues1 = GetInterpolatedValues(1, starttime, endtime,
						interval);

				if (GetNumTags() > 1) {
					myPIValues2 = GetInterpolatedValues(2, starttime, endtime,
							interval);

				}
				if (GetNumTags() > 2) {
					myPIValues3 = GetInterpolatedValues(3, starttime, endtime,
							interval);
				}
				if (GetNumTags() > 3) {
					myPIValues4 = GetInterpolatedValues(4, starttime, endtime,
							interval);
				}
				if (GetNumTags() > 4) {
					myPIValues5 = GetInterpolatedValues(5, starttime, endtime,
							interval);
				}
			} catch (Exception ex) {
				return 2;
			}
		}
		return 0;

	}

	private List<PIValue> GetRecordedValues(int i, String starttime,
			String endtime)
	{
        if (DataAccessMethod == "PIJDBC")
        {
        	return (myPIJDBC_App.GetRecordedValues(i, starttime, endtime));
        }
        if (DataAccessMethod == "PIWS")
        {
        	return (myPIWS_App.GetRecordedValues(i, starttime, endtime));
        }
        if (DataAccessMethod == "PIWA")
        {
        	return (myPIWA_App.GetRecordedValues(i, starttime, endtime));
        }
        return null;		
	}

	private List<PIValue> GetInterpolatedValues(int i, String starttime,
			String endtime, String interval)
	{
        if (DataAccessMethod == "PIJDBC")
        {
    		return (myPIJDBC_App.GetInterpolatedValues(i, starttime, endtime,interval));
        }
        if (DataAccessMethod == "PIWS")
        {
    		return (myPIWS_App.GetInterpolatedValues(i, starttime, endtime,interval));
        }
        if (DataAccessMethod == "PIWA")
        {
    		return (myPIWA_App.GetInterpolatedValues(i, starttime, endtime,interval));
        }
        return null;

	}

	private double[] ConvertTSToDoubleArray(List<PIValue> myPIValues) {
		int ii = 0;
		double[] MySourceTagArrayTSUTC = new double[myPIValues.size()];
		Iterator<PIValue> iter = myPIValues.iterator();

		while (iter.hasNext()) {
			PIValue CurrentValue = iter.next();
			try {
				MySourceTagArrayTSUTC[ii] = CurrentValue.GetUTCTimeStamp();
				ii++;
			} catch (Exception e) {
			}
		}

		return (MySourceTagArrayTSUTC);
	}

	private double[] ConvertValuesToDoubleArray(List<PIValue> myPIValues) {
		int i = 0;
		double[] MySourceTagArrayValues = new double[myPIValues.size()];
		Iterator<PIValue> iter = myPIValues.iterator();

		while (iter.hasNext()) {
			PIValue CurrentValue = iter.next();
			MySourceTagArrayValues[i] = CurrentValue.GetValue();
			i++;
		}

		return (MySourceTagArrayValues);
	}

	private void TransferDataToR() {

		double[] myPIValues1Values = ConvertValuesToDoubleArray(myPIValues1);
		double[] myPIValues1TS = ConvertTSToDoubleArray(myPIValues1);
		engine.assign("tag1val", myPIValues1Values);
		engine.assign("tag1tsd", myPIValues1TS);
		engine.eval("tag1ts<-as.POSIXct(tag1tsd, origin='1970-01-01')");
		engine.eval("tag1<- data.frame(tag1ts,tag1val)");

		if (this.GetNumTags() >= 2) {

			double[] myPIValues2Values = ConvertValuesToDoubleArray(myPIValues2);
			double[] myPIValues2TS = ConvertTSToDoubleArray(myPIValues2);
			engine.assign("tag2val", myPIValues2Values);
			engine.assign("tag2tsd", myPIValues2TS);
			engine.eval("tag2ts<-as.POSIXct(tag2tsd, origin='1970-01-01')");
			engine.eval("tag2<- data.frame(tag2ts,tag2val)");
		}

		if (this.GetNumTags() >= 3) {

			double[] myPIValues3Values = ConvertValuesToDoubleArray(myPIValues3);
			double[] myPIValues3TS = ConvertTSToDoubleArray(myPIValues3);
			engine.assign("tag3val", myPIValues3Values);
			engine.assign("tag3tsd", myPIValues3TS);
			engine.eval("tag3ts<-as.POSIXct(tag3tsd, origin='1970-01-01')");
			engine.eval("tag3<- data.frame(tag3ts,tag3val)");
		}

		if (this.GetNumTags() >= 4) {

			double[] myPIValues4Values = ConvertValuesToDoubleArray(myPIValues4);
			double[] myPIValues4TS = ConvertTSToDoubleArray(myPIValues4);
			engine.assign("tag4val", myPIValues4Values);
			engine.assign("tag4tsd", myPIValues4TS);
			engine.eval("tag4ts<-as.POSIXct(tag4tsd, origin='1970-01-01')");
			engine.eval("tag4<- data.frame(tag4ts,tag4val)");
		}

		if (this.GetNumTags() >= 5) {

			double[] myPIValues5Values = ConvertValuesToDoubleArray(myPIValues5);
			double[] myPIValues5TS = ConvertTSToDoubleArray(myPIValues5);
			engine.assign("tag5val", myPIValues5Values);
			engine.assign("tag5tsd", myPIValues5TS);
			engine.eval("tag5ts<-as.POSIXct(tag5tsd, origin='1970-01-01')");
			engine.eval("tag5<- data.frame(tag5ts,tag5val)");
		}
	}

	public void GenerateGhaphic(String par1, String par2, String par3,
			String par4) {
		TransferDataToR();
		if (JavaGdWindowClose==true)
		{
			engine.eval("library(JavaGD)");
			//engine.eval("Sys.putenv('JAVAGD_CLASS_NAME'='MyJavaGD1')");
			engine.eval("JavaGD()");
			//JavaGdWindowClose=false;
			
		}

		if (this.RFunction == "PI Histogram") {

			String tag1name = "\"" + MyTagName1 + "\"";
			engine.eval("PI_Histogram(tag1val," + par1 + "," + tag1name + ","
					+ par2 + "," + par3 + ")");
		}

		if (this.RFunction == "PI Density Plot") {
			// PI_Density_Plot <-
			// function(v,s_col="red",s_border="blue",s_type="l",n_asp=NULL)
			String mpar1 = "\"" + par1 + "\"";
			String mpar2 = "\"" + par2 + "\"";
			String mpar3 = "\"" + par3 + "\"";
			String tag1name = "\"" + MyTagName1 + "\"";
			engine.eval("PI_Density_Plot(tag1$tag1val," + mpar1 + "," + mpar2
					+ "," + mpar3 + "," + par4 + "," + tag1name + ")");
		}

		if (this.RFunction == "PI Density Compare for two tags") {
			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			engine.eval("PI_Density_Compare_TwoTags(tag1$tag1val,tag2$tag2val,"
					+ tag1name + "," + tag2name + ")");
		}

		if (this.RFunction == "PI Density Compare for three tags") {
			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			String tag3name = "\"" + MyTagName3 + "\"";
			engine.eval("PI_Density_Compare_ThreeTags(tag1$tag1val,tag2$tag2val,tag3$tag3val,"
					+ tag1name + "," + tag2name + "," + tag3name + ")");
		}

		if (this.RFunction == "PI Box Plot") {
			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			engine.eval("PI_Box_Plot(tag1$tag1val,tag2$tag2val," + tag1name
					+ "," + tag2name + "," + par1 + "," + par2 + "," + par3
					+ ")");
		}
		if (this.RFunction == "PI Regular Correlation") {

			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			engine.eval("PI_Regular_Correlation(tag1$tag1val,tag2$tag2val,"
					+ tag1name + "," + tag2name + ")");
			REXP ResultsShown = engine.eval("cor(tag1$tag1val,tag2$tag2val)");
			ValueToBeShown = ResultsShown.asDouble();
		}

		if (this.RFunction == "PI Smooth Scatter") {

			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			engine.eval("PI_Smooth_Scatter(tag1$tag1val,tag2$tag2val,"
					+ tag1name + "," + tag2name + ")");
		}

		if (this.RFunction == "PI Multi-Correlation for three tags") {
			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			String tag3name = "\"" + MyTagName3 + "\"";
			engine.eval("impact3<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val)");
			// engine.eval("PI_Multi_Correlation3(impact3,"+ tag1name + "," +
			// tag2name + "," + tag3name +")");
			engine.eval("tn3<-c(" + tag1name + "," + tag2name + "," + tag3name
					+ ")");
			engine.eval("PI_Multi_Correlation3(impact3,tn3)");
		}

		if (this.RFunction == "PI Multi-Correlation for four tags") {
			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			String tag3name = "\"" + MyTagName3 + "\"";
			String tag4name = "\"" + MyTagName4 + "\"";
			engine.eval("impact4<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val)");
			engine.eval("tn4<-c(" + tag1name + "," + tag2name + "," + tag3name
					+ "," + tag4name + ")");
			engine.eval("PI_Multi_Correlation4(impact4,tn4)");
		}

		if (this.RFunction == "PI Multi-Correlation for five tags") {
			String tag1name = "\"" + MyTagName1 + "\"";
			String tag2name = "\"" + MyTagName2 + "\"";
			String tag3name = "\"" + MyTagName3 + "\"";
			String tag4name = "\"" + MyTagName4 + "\"";
			String tag5name = "\"" + MyTagName5 + "\"";
			engine.eval("impact5<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val,tag5$tag5val)");
			engine.eval("tn5<-c(" + tag1name + "," + tag2name + "," + tag3name
					+ "," + tag4name + "," + tag5name + ")");
			engine.eval("PI_Multi_Correlation5(impact5,tn5)");

		}
	}

	public int IntCountPIValues(int n) {
		if (n == 1) {
			return (myPIValues1.size());
		}
		if (n == 2) {
			return (myPIValues2.size());
		}
		if (n == 3) {
			return (myPIValues3.size());
		}
		if (n == 4) {
			return (myPIValues4.size());
		}
		if (n == 5) {
			return (myPIValues5.size());
		}
		return -1;
	}
	
    private void Disconnect()
    {
        if (myPIJDBC_App != null)
        {
        	myPIJDBC_App.Disconnect();
        	myPIJDBC_App = null;
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
