import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PIValuesList extends ArrayList<PIValues> {
	private static final long serialVersionUID = 1L;

	public PIValuesList() {

	}

	public PIValuesList(JSONObject valuesList) {
		int a = 1;
		a++;
		JSONArray items = (JSONArray) valuesList.get("Items");

		for (int i = 0; i < items.size(); i++) {
			JSONObject item = (JSONObject) items.get(i);
			try
			{			
				PIValues vals = ConvertToPIValues(item);
				this.add(vals);
			}
			catch(ParseException ex)
			{
				
			}
		
		}
	}

	private PIValues ConvertToPIValues(JSONObject values) throws ParseException {
		PIValues piValues = new PIValues();
		JSONArray items = (JSONArray) values.get("Items");
		piValues.PIPointName = (String) values.get("Name");
		for (int i = 0; i < items.size(); i++) {
			JSONObject item = (JSONObject) items.get(i);
			double dValue = (double)item.get("Value");
			String dtString = (String)item.get("Timestamp");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));			
			Date dt = sdf.parse(dtString);
			PIValue val  = new PIValue(dValue,dt);
			piValues.add(val);
		}
	
	
		return piValues;
	}
}