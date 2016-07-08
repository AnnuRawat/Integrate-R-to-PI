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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.*;
import org.json.simple.parser.*;

public class PIWebAPI_Wrapper implements IPIDeveloperTech {
	private String baseUrl = "https://marc-web-sql.marc.net/piwebapi/";
	private Map<String, String> PIServersWebIdDic;
	private String piDataArchiveName = "";
	private String[] webIds = null;

	public PIWebAPI_Wrapper() {

	}

	public String GetVersion() {
		String url = baseUrl + "system/versions";
		JSONObject result = MakeRequestObject(url);
		// return (result.get("OSIsoft.REST").get("FullVersion").get("Value"));
		return null;
	}

	public Boolean Connect(String PIDataArchiveName) {
		piDataArchiveName = PIDataArchiveName;
		if (PIDataArchiveName == null) {
			return false;
		}
		try {
			String url = baseUrl;
			JSONObject result = MakeRequestObject(url);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public String[] GetPIDataArchiveNamesList() {
		String url = baseUrl + "dataservers";
		JSONObject result = MakeRequestObject(url);
		String[] piDataArchiveNames = new String[2];
		// PIServersWebIdDic = new HashMap<String, String>();

		// for (int i = 0; i < result.Items.Count; i++) {
		// piDataArchiveNames[i] = result.Items[i].Name;
		// }
		return piDataArchiveNames;
	}

	private JSONObject GenerateSingleBatch(String piPointName) {
		JSONObject singleBatch = new JSONObject();

		singleBatch.put("Resource",
				"https://marc-web-sql.marc.net/piwebapi/points?path=\\\\" + piDataArchiveName + "\\" + piPointName);
		singleBatch.put("Method", "GET");
		return singleBatch;
	}

	public Boolean ValidateTagNames(String[] piPoints) {
		JSONObject postBatch = new JSONObject();
		webIds = new String[piPoints.length];
		for (int i = 0; i < piPoints.length; i++) {
			postBatch.put(i, GenerateSingleBatch(piPoints[i]));
		}
		JSONObject piPointWebIds = SendBatchRequest(postBatch);
		for (int i = 0; i < piPoints.length; i++) {

		JSONObject piPointInfo = (JSONObject)piPointWebIds.get(Integer.toString(i));
		int status = Integer.valueOf(piPointInfo.get("Status").toString());
		if (status != 200) {
		 return false;
		 } else {
			 JSONObject content = (JSONObject)piPointInfo.get("Content");
			 webIds[i] = (String) content.get("WebId");
		 }
		}
		return true;
	}

	public PIValuesList GetRecordedValues(String startTime, String endTime) {
		String webIdsString = GetWebIdsString();
		String url = baseUrl + "streamsets/recorded?starttime=" + startTime + "&endtime=" + endTime + "&"
				+ webIdsString;
		JSONObject result = MakeRequestObject(url);
		return new PIValuesList(result);
	}

	public PIValuesList GetInterpolatedValues(String startTime, String endTime, String interval) {
		String webIdsString = GetWebIdsString();
		String url = baseUrl + "streamsets/interpolated?starttime=" + startTime + "&endtime=" + endTime + "&interval="
				+ interval + "&" + webIdsString;
		JSONObject result = MakeRequestObject(url);
		return new PIValuesList(result);
	}

	public String GetWebIdsString() {
		String webIdsString = "";
		for (int i = 0; i < webIds.length; i++) {
			webIdsString = webIdsString + "&webId=" + webIds[i];
		}
		return webIdsString.substring(1);
	}

	public String GetServerName() {
		return piDataArchiveName;
	}


	private JSONObject SendBatchRequest(JSONObject postBatch) {
		JSONParser myJSONParser = new JSONParser();
		JSONObject myJSONObject = null;
		try {

			String responseString = null;

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(baseUrl + "/batch");
			StringEntity postingString = new StringEntity(postBatch.toString());
			request.setEntity(postingString);
			request.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity);
			 myJSONObject = (JSONObject) myJSONParser.parse(responseString);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Bad JSONObject");
		}
		return myJSONObject;
	}

	private JSONObject MakeRequestObject(String url) {
		JSONObject myJSONObject = null;
		try {

			JSONParser myJSONParser = new JSONParser();
			myJSONObject = (JSONObject) myJSONParser.parse(GetHttpResponse(url));

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

	public void Disconnect() {

	}

}
