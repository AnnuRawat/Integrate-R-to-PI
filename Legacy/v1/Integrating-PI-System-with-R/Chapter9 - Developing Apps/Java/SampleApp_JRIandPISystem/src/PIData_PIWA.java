import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.*;
import org.json.simple.parser.*;

public class PIData_PIWA {
	private String baseUrl = "https://osi-serv.osibr.com/piwebapi/";
	private Map<String, String> PIServersWebIdDic;
	private String PIServerName = "";
	private String MyTagName1 = "";
	private String MyTagName2 = "";
	private String MyTagName3 = "";
	private String MyTagName4 = "";
	private String MyTagName5 = "";
	private int isConnected;

	public PIData_PIWA() {
	}

	public String GetPIWAversion() {
		String VersionUrl = baseUrl + "/version";
		JSONObject myJSONObject = MakeRequestObject(VersionUrl);
		return ("1.0.0.0");
	}

	public int ConnectToPIServer(String piservername) {
		try {
			String PIServerUrl = baseUrl + "dataservers?name=" + piservername;
			JSONArray myJSONPIServerObj = MakeRequestArray(PIServerUrl);
			JSONObject myJSONObject = MakeRequestObject(PIServerUrl);
			if (myJSONObject != null) {
				PIServerName = piservername;
				isConnected = 1;
			}
		} catch (Exception ex) {
			isConnected = 0;
		}

		return isConnected;
	}

	public String[] GetPIServersList() {
		String PIServersUrl = baseUrl + "dataservers";
		JSONArray PIServersList = MakeRequestArray(PIServersUrl);
		PIServersWebIdDic = null;
		PIServersWebIdDic = new HashMap<String, String>();

		String[] sPIServersList = new String[PIServersList.size()];
		for (int i = 0; i < PIServersList.size(); i++) 
		{
			JSONObject myObj = (JSONObject)PIServersList.get(i);
			sPIServersList[i] = myObj.get("Name").toString();
		}		
		return (sPIServersList);
	}

	public int ValidateTagNames(String pitag1, String pitag2, String pitag3,
			String pitag4, String pitag5, int NumTags) {

		try {
			String PIPointUrl1 = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + pitag1;
			JSONObject MyTag1 = MakeRequestObject(PIPointUrl1);
			Object Id = MyTag1.get("Id");
			MyTagName1 = pitag1;

			if (NumTags > 1) {
				String PIPointUrl2 = "http://marc-web-sql/piwebapi/piservers/"
						+ PIServerName + "/points/" + pitag2;
				JSONObject MyTag2 = MakeRequestObject(PIPointUrl2);
				Object Id2 = MyTag2.get("Id");
				MyTagName2 = pitag2;
			}
			if (NumTags > 2) {
				String PIPointUrl3 = "http://marc-web-sql/piwebapi/piservers/"
						+ PIServerName + "/points/" + pitag3;
				JSONObject MyTag3 = MakeRequestObject(PIPointUrl3);
				Object Id3 = MyTag3.get("Id");
				MyTagName3 = pitag3;
			}
			if (NumTags > 3) {
				String PIPointUrl4 = "http://marc-web-sql/piwebapi/piservers/"
						+ PIServerName + "/points/" + pitag4;
				JSONObject MyTag4 = MakeRequestObject(PIPointUrl4);
				Object Id4 = MyTag4.get("Id");
				MyTagName4 = pitag4;
			}
			if (NumTags > 4) {
				String PIPointUrl5 = "http://marc-web-sql/piwebapi/piservers/"
						+ PIServerName + "/points/" + pitag5;
				JSONObject MyTag5 = MakeRequestObject(PIPointUrl5);
				Object Id5 = MyTag5.get("Id");
				MyTagName5 = pitag5;
			}

		} catch (Exception ex) {
			return 1;
		}

		return 0;

	}

	public List<PIValue> GetRecordedValues(int i, String starttime,
			String endtime) {
		String PIPointRecData1Url;
		JSONArray PIPointRecData;
		if (i == 1) {
			PIPointRecData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName1
					+ "/recorded?starttime=" + starttime + "&endtime="
					+ endtime;
			PIPointRecData = MakeRequestArray(PIPointRecData1Url);
		} else if (i == 2) {
			PIPointRecData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName2
					+ "/recorded?starttime=" + starttime + "&endtime="
					+ endtime;
			PIPointRecData = MakeRequestArray(PIPointRecData1Url);
		} else if (i == 3) {
			PIPointRecData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName3
					+ "/recorded?starttime=" + starttime + "&endtime="
					+ endtime;
			PIPointRecData = MakeRequestArray(PIPointRecData1Url);
		} else if (i == 4) {
			PIPointRecData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName4
					+ "/recorded?starttime=" + starttime + "&endtime="
					+ endtime;
			PIPointRecData = MakeRequestArray(PIPointRecData1Url);
		} else {
			PIPointRecData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName5
					+ "/recorded?starttime=" + starttime + "&endtime="
					+ endtime;
			PIPointRecData = MakeRequestArray(PIPointRecData1Url);
		}
		return (ConvertToPIValues(PIPointRecData));
	}

	public List<PIValue> GetInterpolatedValues(int i, String starttime,
			String endtime, String interval) {
		String PIPointIntData1Url;
		JSONArray PIPointIntData;
		if (i == 1) {
			PIPointIntData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName1
					+ "/interpolated?starttime=" + starttime + "&endtime="
					+ endtime + "&interval=" + interval;
			PIPointIntData = MakeRequestArray(PIPointIntData1Url);
		} else if (i == 2) {
			PIPointIntData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName2
					+ "/interpolated?starttime=" + starttime + "&endtime="
					+ endtime + "&interval=" + interval;
			PIPointIntData = MakeRequestArray(PIPointIntData1Url);
		} else if (i == 3) {
			PIPointIntData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName3
					+ "/interpolated?starttime=" + starttime + "&endtime="
					+ endtime + "&interval=" + interval;
			PIPointIntData = MakeRequestArray(PIPointIntData1Url);
		} else if (i == 4) {
			PIPointIntData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName4
					+ "/interpolated?starttime=" + starttime + "&endtime="
					+ endtime + "&interval=" + interval;
			PIPointIntData = MakeRequestArray(PIPointIntData1Url);
		} else {
			PIPointIntData1Url = "http://marc-web-sql/piwebapi/piservers/"
					+ PIServerName + "/points/" + this.MyTagName5
					+ "/interpolated?starttime=" + starttime + "&endtime="
					+ endtime + "&interval=" + interval;
			PIPointIntData = MakeRequestArray(PIPointIntData1Url);
		}
		return (ConvertToPIValues(PIPointIntData));
	}

	private JSONArray MakeRequestArray(String url) {
		JSONArray myJSONArray = null;
		try {

			JSONParser myJSONParser = new JSONParser();
			myJSONArray = (JSONArray) myJSONParser.parse(GetHttpResponse(url));

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new IllegalArgumentException("Bad JSONArray");
		}
		return myJSONArray;
	}

	private JSONObject MakeRequestObject(String url) {
		JSONObject myJSONObject = null;
		try {

			JSONParser myJSONParser = new JSONParser();
			myJSONObject = (JSONObject) myJSONParser
					.parse(GetHttpResponse(url));

		} catch (Exception ex) {
			throw new IllegalArgumentException("Bad JSONObject");
		}
		return myJSONObject;
	}

	private String GetHttpResponse(String url) {
		String responseString = null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Bad HTTP Response");
		}

		return (responseString);

	}

	private List<PIValue> ConvertToPIValues(JSONArray DataJsonArray) {

		List<PIValue> myPIValues = new ArrayList<PIValue>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		for (int i = 0; i < DataJsonArray.size(); i++) {

			JSONObject myObj = (JSONObject) DataJsonArray.get(i);
			double Value = (double) myObj.get("Value");
			String TimeString = myObj.get("Timestamp").toString();
			java.util.Date Timestamp = null;
			try {
				Timestamp = sdf.parse(TimeString);
			} catch (Exception ex) {
			}
			myPIValues.add(new PIValue(Value, Timestamp));
		}

		return myPIValues;

	}

	public void Disconnect() {

	}
}
