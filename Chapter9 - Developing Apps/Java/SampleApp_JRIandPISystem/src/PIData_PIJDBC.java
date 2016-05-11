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

public class PIData_PIJDBC 
{

    private String PIServerName = "";
    private String MyTagName1 = "";
    private String MyTagName2 = "";
    private String MyTagName3 = "";
    private String MyTagName4 = "";
    private String MyTagName5= "";
	private String url = null;
	private Connection con = null;
	private String driver = "com.osisoft.jdbc.Driver";
	private Properties plist;
	private int isConnected;

    public PIData_PIJDBC()
    {
        
    }


    public String GetPIJDBCversion()
    {
        return ("1.0.0.0");
    }





    public int ConnectToPIServer(String piservername)
    {
    	isConnected = 0;
		try 
		{
			PIServerName = piservername;
			url = "jdbc:pisql://MARC-WEB-SQL/Data Source=" + PIServerName
					+ "; Integrated Security=SSPI";
			plist = new Properties();
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, plist);
			DatabaseMetaData md = con.getMetaData();
			System.out
					.println(md.getDriverName() + " " + md.getDriverVersion());
			System.out.println(md.getDatabaseProductName());
			System.out.println(md.getDatabaseProductVersion() + "\n");
			isConnected = 1;
		} 
		catch (Exception e) 
		{
			isConnected = 0;
		}
		return isConnected;
    }

    public String[] GetPIServersList()
    {
        return (new String[]{"MARC-PI2014","TEST"});
    }



	public int ValidateTagNames(String pitag1, String pitag2, String pitag3,String pitag4, String pitag5, int NumTags) {
		try {
			ValidateTagName(pitag1);
			MyTagName1=pitag1;
			if (NumTags > 1) {
				ValidateTagName(pitag2);
				MyTagName2=pitag2;
			}
			if (NumTags > 2) {
				ValidateTagName(pitag3);
				MyTagName3=pitag3;
			}
			
			
			if (NumTags > 3) {
				ValidateTagName(pitag4);
				MyTagName4=pitag4;

			}
			if (NumTags > 4) {
				ValidateTagName(pitag5);
				MyTagName5=pitag5;
			}

		} catch (Exception ex) {
			return 1;
		}
		return 0;
	}


	private void ValidateTagName(String TagName) {
		String result = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			con = DriverManager.getConnection(url, plist);			
			stmt = con.createStatement();		
			String query = "SELECT count(tag) FROM pipoint.pipoint where tag=\'"
					+ TagName + "\'";
			rs = stmt.executeQuery(query);
			rs.first();
			result = rs.getString(1);
			
		} 
	
		catch (Exception e) 
		{
			e.printStackTrace();


		} 
		finally
		{
			if (con != null) {
				try {
					rs.close();
					stmt=null;
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (result.equals(new String("0"))) {
			throw new IllegalArgumentException("Invalid tags");

		}

	}
	


    public List<PIValue> GetRecordedValues(int i, String starttime, String endtime)
    {

        if (i==1)
        {
        	return (ExecutePIQuery("SELECT value,time,status FROM piarchive.picomp WHERE tag = \'" + this.MyTagName1 + "\' AND time BETWEEN \'" + starttime + "\' AND \'" + endtime + "\'"));  
        }
        else if (i==2)
        {
        	return (ExecutePIQuery("SELECT value,time,status FROM piarchive.picomp WHERE tag = \'" + this.MyTagName2 + "\' AND time BETWEEN \'" + starttime + "\' AND \'" + endtime + "\'"));  
               
        }
        else if (i==3)
        {
         	return (ExecutePIQuery("SELECT value,time,status FROM piarchive.picomp WHERE tag = \'" + this.MyTagName3 + "\' AND time BETWEEN \'" + starttime + "\' AND \'" + endtime + "\'"));  
         }
        else if (i==4)
        {
        	return (ExecutePIQuery("SELECT value,time,status FROM piarchive.picomp WHERE tag = \'" + this.MyTagName4 + "\' AND time BETWEEN \'" + starttime + "\' AND \'" + endtime + "\'"));  
                
        }
        else
        {
        	return (ExecutePIQuery("SELECT value,time,status FROM piarchive.picomp WHERE tag = \'" + this.MyTagName5 + "\' AND time BETWEEN \'" + starttime + "\' AND \'" + endtime + "\'"));  
        }
    }

    public List<PIValue> GetInterpolatedValues(int i, String starttime, String endtime, String interval)
    {
    
    	 if (i==1)
         {
    		 return ExecutePIQuery("SELECT value,time,status FROM [piarchive].[piinterp] WHERE tag = \'" + this.MyTagName1 + "\' AND time BETWEEN \'" + starttime  + "\' AND \'" + endtime + "\' AND timestep=\'" + interval +"\'");
         }
         else if (i==2)
         {
        	 return ExecutePIQuery("SELECT value,time,status FROM [piarchive].[piinterp] WHERE tag = \'" + this.MyTagName2 + "\' AND time BETWEEN \'" + starttime  + "\' AND \'" + endtime + "\' AND timestep=\'" + interval +"\'");
                
         }
         else if (i==3)
         {
        	 return ExecutePIQuery("SELECT value,time,status FROM [piarchive].[piinterp] WHERE tag = \'" + this.MyTagName3 + "\' AND time BETWEEN \'" + starttime  + "\' AND \'" + endtime + "\' AND timestep=\'" + interval +"\'");
             }
         else if (i==4)
         {
        	 return ExecutePIQuery("SELECT value,time,status FROM [piarchive].[piinterp] WHERE tag = \'" + this.MyTagName4 + "\' AND time BETWEEN \'" + starttime  + "\' AND \'" + endtime + "\' AND timestep=\'" + interval +"\'");
                   
         }
         else
         {
        	 return ExecutePIQuery("SELECT value,time,status FROM [piarchive].[piinterp] WHERE tag = \'" + this.MyTagName5 + "\' AND time BETWEEN \'" + starttime  + "\' AND \'" + endtime + "\' AND timestep=\'" + interval +"\'");
         }
    }




	
		private List<PIValue> ExecutePIQuery(String query) {
		System.out.println(query);
		ResultSet rs = null;
		Statement stmt = null;
		List<PIValue> myPIValues = new ArrayList<PIValue>();
		try {
			con = DriverManager.getConnection(url, plist);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			double value;
			Date time;
			int status;
			rs.beforeFirst();
			while (rs.next()) 
			{
				value = Double.parseDouble(rs.getString(1));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				time = new java.sql.Date(format.parse(rs.getString(2))
						.getTime());
				status = Integer.parseInt(rs.getString(3));
				PIValue myValue = new PIValue(value, time, status);
				myPIValues.add(myValue);

			}

		} 
		
		catch (Exception e) 
		{
			//e.printStackTrace();
			throw new IllegalArgumentException("Invalid tags");

		} 
		finally
		{
			if (con != null) {
				try {
					rs.close();
					stmt=null;
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

		return myPIValues;

	}

    public void Disconnect()
    {
    	
    
    }
}
