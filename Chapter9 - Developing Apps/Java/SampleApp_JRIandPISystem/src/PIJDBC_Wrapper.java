import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class PIJDBC_Wrapper implements IPIDeveloperTech {

	private String PIServerName = "";
	private String[] pitags = null;
	private String url = null;
	private Connection con = null;
	private String driver = "com.osisoft.jdbc.Driver";
	private Properties plist;
	private Boolean isConnected;

	public PIJDBC_Wrapper() {

	}

	public String[] GetPIServersList() {
		return (new String[] { "MARC-PI2016" });
	}

	public String GetVersion() {
		return ("1.0.0.0");
	}

	public Boolean Connect(String PIDataArchiveName) {
		isConnected = false;
		try {
			PIServerName = PIDataArchiveName;
			url = "jdbc:pisql://MARC-WEB-SQL/Data Source=" + PIServerName + "; Integrated Security=SSPI";
			plist = new Properties();
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, plist);
			DatabaseMetaData md = con.getMetaData();
			System.out.println(md.getDriverName() + " " + md.getDriverVersion());
			System.out.println(md.getDatabaseProductName());
			System.out.println(md.getDatabaseProductVersion() + "\n");
			isConnected = true;
		} catch (Exception e) {
			isConnected = false;
		}
		return isConnected;
	}

	public String[] GetPIDataArchiveNamesList() {
		return null;
	}

	public Boolean ValidateTagNames(String[] piPoints) {
		pitags = piPoints;
		try {
			ValidateTagName(piPoints[0]);
			if (piPoints.length > 1) {
				ValidateTagName(piPoints[1]);
			}
			if (piPoints.length > 2) {
				ValidateTagName(piPoints[2]);
			}

			if (piPoints.length > 3) {
				ValidateTagName(piPoints[3]);
			}
			if (piPoints.length > 4) {
				ValidateTagName(piPoints[4]);
			}

		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public PIValuesList GetRecordedValues(String startTime, String endTime) {
		PIValuesList piValuesList = new PIValuesList();
		for (int i = 0; i < pitags.length; i++) {
			PIValues values = ExecutePIQuery("SELECT value,time,status FROM piarchive.picomp WHERE tag = \'" + pitags[i]
					+ "\' AND time BETWEEN \'" + startTime + "\' AND \'" + endTime + "\'");
			values.PIPointName = pitags[i];
			piValuesList.add(values);
		}
		return piValuesList;

	}

	public PIValuesList GetInterpolatedValues(String startTime, String endTime, String interval) {
		PIValuesList piValuesList = new PIValuesList();
		for (int i = 0; i < pitags.length; i++) {
			PIValues values = ExecutePIQuery("SELECT value,time,status FROM [piarchive].[piinterp] WHERE tag = \'"
					+ pitags[i] + "\' AND time BETWEEN \'" + startTime + "\' AND \'" + endTime + "\' AND timestep=\'"
					+ interval + "\'");
			values.PIPointName = pitags[i];
			piValuesList.add(values);
		}
		return piValuesList;
	}

	public String GetServerName() {
		return PIServerName;
	}

	private void ValidateTagName(String tagName) {
		String result = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			con = DriverManager.getConnection(url, plist);
			stmt = con.createStatement();
			String query = "SELECT count(tag) FROM pipoint.pipoint where tag=\'" + tagName + "\'";
			rs = stmt.executeQuery(query);
			rs.first();
			result = rs.getString(1);
		}

		catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (con != null) {
				try {
					rs.close();
					stmt = null;
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		if (result.equals(new String("0"))) {
			throw new IllegalArgumentException("Invalid tags");

		}

	}

	private PIValues ExecutePIQuery(String query) {
		System.out.println(query);
		ResultSet rs = null;
		Statement stmt = null;
		PIValues values = new PIValues();
		try {
			con = DriverManager.getConnection(url, plist);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			double value;
			Date time;
			int status;
			rs.beforeFirst();
			while (rs.next()) {
				value = Double.parseDouble(rs.getString(1));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				time = new java.sql.Date(format.parse(rs.getString(2)).getTime());
				status = Integer.parseInt(rs.getString(3));
				PIValue myValue = new PIValue(value, time, status);
				values.add(myValue);
			}
		}

		catch (Exception e) {
			// e.printStackTrace();
			throw new IllegalArgumentException("Invalid tags");

		} finally {
			if (con != null) {
				try {
					rs.close();
					stmt = null;
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return values;

	}

	public void Disconnect() {

	}
}
