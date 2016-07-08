
import java.util.List;
import java.util.*;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class Application {
	private boolean JavaGdWindowClose = true;
	private String PIServerName = null;
	private String Mode = null;
	private String RFunction = null;
	private PIValuesList piValuesList = null;
	public Boolean isConnected = false;
	private Rengine engine;
	private int NumberTags = -1;
	public double ValueToBeShown = -1;
	public int ProgramCurrentStep = -1;
	private IPIDeveloperTech piDevWrapper = null;

	private String DataAccessMethod = "";

	public void SetMode(String mode) {
		this.Mode = mode;
	}

	public Application() {
		DataAccessMethod = "PIJDBC";
		this.InicializeRNet();
		this.Inicialize();
	}

	private void InicializeRNet() {

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
		String rfilepath = "C:\\\\Program Files\\\\R\\\\R-3.3.1\\\\library\\\\Functions.R";
		engine.eval("source(\"" + rfilepath + "\")");

	}

	public void Inicialize() {
		if (piDevWrapper !=null)
		{
			piDevWrapper.Disconnect();
		}
		if (DataAccessMethod == "PIJDBC") {
			piDevWrapper = new PIJDBC_Wrapper();
		}
		if (DataAccessMethod == "PIWA") {
			piDevWrapper = new PIWebAPI_Wrapper();
		}
	}

	public String GetMode() {
		return (this.Mode);
	}

	public void SetDataAccessMethod(String dataAccessMethod) {
		DataAccessMethod = dataAccessMethod;
	}

	public String GetDataAccessMethod() {
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

	public String GetVersion() {
		return piDevWrapper.GetVersion();
	}

	public void Connect(String PIServerName) {
		isConnected = piDevWrapper.Connect(PIServerName);
	}

	private Boolean ValidateTagNames(String[] piPoints) {
		return piDevWrapper.ValidateTagNames(piPoints);

	}

	private Boolean TransferDataFromPI(String[] piPointNamesArray, String starttime, String endtime, String interval) {
		if (this.Mode == "Recorded") {
			try {
				piValuesList = piDevWrapper.GetRecordedValues(starttime, endtime);

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return false;
			}
		}

		if (this.Mode == "Interpolated") {
			try {
				piValuesList = piDevWrapper.GetInterpolatedValues(starttime, endtime, interval);

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return false;
			}
		}
		return true;

	}

	public int GetPIData(String pitag1, String pitag2, String pitag3, String pitag4, String pitag5, String starttime,
			String endtime, String interval) {

		List<String> piPointNames = new ArrayList<String>();
		piPointNames.add(pitag1);
		if (this.NumberTags > 1)
			piPointNames.add(pitag2);
		if (this.NumberTags > 2)
			piPointNames.add(pitag3);
		if (this.NumberTags > 3)
			piPointNames.add(pitag4);
		if (this.NumberTags > 4)
			piPointNames.add(pitag5);

		String[] piPointNamesArray= new String[piPointNames.size()];
		piPointNamesArray = piPointNames.toArray(piPointNamesArray);
		
		Boolean tagsAreValid = ValidateTagNames(piPointNamesArray);
		if (tagsAreValid == false) {
			return 1;
		}

		Boolean transferSuccess = TransferDataFromPI(piPointNamesArray, starttime, endtime, interval);
		if (transferSuccess == false) {
			return 2;
		}

		return 0;
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

		for (int i = 1; i <= piValuesList.size(); i++) {
			double[] values = ConvertValuesToDoubleArray(piValuesList.get(i - 1));
			double[] ts = ConvertTSToDoubleArray(piValuesList.get(i - 1));
			engine.assign("tag" + i + "val", values);
			engine.assign("tag" + i + "tsd", ts);
			engine.eval("tag" + i + "ts<-as.POSIXct(tag" + i + "tsd, origin='1970-01-01')");
			engine.eval("tag" + i + "<- data.frame(tag" + i + "ts,tag" + i + "val)");
		}
	}

	public void GenerateGhaphic(String par1, String par2, String par3, String par4) {
		TransferDataToR();

		if (JavaGdWindowClose == true) {
			engine.eval("library(JavaGD)");
			// engine.eval("Sys.putenv('JAVAGD_CLASS_NAME'='MyJavaGD1')");
			engine.eval("JavaGD()");
			// JavaGdWindowClose=false;
		}

		String tag1name = "";
		String tag2name = "";
		String tag3name = "";
		String tag4name = "";
		String tag5name = "";

		if (piValuesList.size() > 0)
			tag1name = "\"" + piValuesList.get(0).PIPointName + "\"";
		if (piValuesList.size() > 1)
			tag2name = "\"" + piValuesList.get(1).PIPointName + "\"";
		if (piValuesList.size() > 2)
			tag3name = "\"" + piValuesList.get(2).PIPointName + "\"";
		if (piValuesList.size() > 3)
			tag4name = "\"" + piValuesList.get(3).PIPointName + "\"";
		if (piValuesList.size() > 4)
			tag5name = "\"" + piValuesList.get(4).PIPointName + "\"";

		String mpar1 = "\"" + par1 + "\"";
		String mpar2 = "\"" + par2 + "\"";
		String mpar3 = "\"" + par3 + "\"";

		if (this.RFunction == "PI Histogram") {
			engine.eval("PI_Histogram(tag1val," + par1 + "," + tag1name + "," + par2 + "," + par3 + ")");
		}

		if (this.RFunction == "PI Density Plot") {

			engine.eval("PI_Density_Plot(tag1$tag1val," + mpar1 + "," + mpar2 + "," + mpar3 + "," + par4 + ","
					+ tag1name + ")");
		}

		if (this.RFunction == "PI Density Compare for two tags") {
			engine.eval("PI_Density_Compare_TwoTags(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
		}

		if (this.RFunction == "PI Density Compare for three tags") {
			engine.eval("PI_Density_Compare_ThreeTags(tag1$tag1val,tag2$tag2val,tag3$tag3val," + tag1name + ","
					+ tag2name + "," + tag3name + ")");
		}

		if (this.RFunction == "PI Box Plot") {
			engine.eval("PI_Box_Plot(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + "," + par1 + "," + par2
					+ "," + par3 + ")");
		}
		if (this.RFunction == "PI Regular Correlation") {
			engine.eval("PI_Regular_Correlation(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
			REXP ResultsShown = engine.eval("cor(tag1$tag1val,tag2$tag2val)");
			ValueToBeShown = ResultsShown.asDouble();
		}

		if (this.RFunction == "PI Smooth Scatter") {
			engine.eval("PI_Smooth_Scatter(tag1$tag1val,tag2$tag2val," + tag1name + "," + tag2name + ")");
		}

		if (this.RFunction == "PI Multi-Correlation for three tags") {
			engine.eval("impact3<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val)");
			// engine.eval("PI_Multi_Correlation3(impact3,"+ tag1name + "," +
			// tag2name + "," + tag3name +")");
			engine.eval("tn3<-c(" + tag1name + "," + tag2name + "," + tag3name + ")");
			engine.eval("PI_Multi_Correlation3(impact3,tn3)");
		}

		if (this.RFunction == "PI Multi-Correlation for four tags") {
			engine.eval("impact4<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val)");
			engine.eval("tn4<-c(" + tag1name + "," + tag2name + "," + tag3name + "," + tag4name + ")");
			engine.eval("PI_Multi_Correlation4(impact4,tn4)");
		}

		if (this.RFunction == "PI Multi-Correlation for five tags") {

			engine.eval("impact5<-data.frame(tag1$tag1val,tag2$tag2val,tag3$tag3val,tag4$tag4val,tag5$tag5val)");
			engine.eval("tn5<-c(" + tag1name + "," + tag2name + "," + tag3name + "," + tag4name + "," + tag5name + ")");
			engine.eval("PI_Multi_Correlation5(impact5,tn5)");

		}
	}

	public int IntCountPIValues(int n) {
		PIValues values = piValuesList.get(n - 1);
		return values.size();
	}

	private void Disconnect() {
		piDevWrapper.Disconnect();
	}

}
