import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

import com.osisoft.xml.services.pidataservice.ArrayOfString;
import com.osisoft.xml.services.pidataservice.ArrayOfTimeSeries;
import com.osisoft.xml.services.pidataservice.IPITimeSeries;
import com.osisoft.xml.services.pidataservice.PITimeSeriesService;
import com.osisoft.xml.services.pidataservice.TimeSeries;
import com.osisoft.xml.services.pidataservice.TimedValue;


import com.osisoft.xml.services.pidataservice.PIArcManner;
import com.osisoft.xml.services.pidataservice.TimeRange;
import com.osisoft.xml.services.pidataservice.PIArcDataRequest;
import com.osisoft.xml.services.pidataservice.ArrayOfPIArcDataRequest;





public class PIData_PIWS 
{

    private String PIServerName = "";
	private int isConnected;
	private PITimeSeriesService service;
	private IPITimeSeries ptsc;
	private ArrayOfString paths;

    public PIData_PIWS()
    {
    		try {
            
           service = new PITimeSeriesService(new URL("http://marc-web-sql.marc.net:82/PIWS2012/PITimeSeries.svc?wsdl"),
                    new QName("http://xml.osisoft.com/services/PIDataService", "PITimeSeriesService"));
            ptsc = service.getBasicEndpoint(); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
    }


    public String GetPIWSversion()
    {
    	return ptsc.getProductVersion();
    }


    public int ConnectToPIServer(String piservername)
    {
        
        ArrayOfString pathsc = new ArrayOfString();
        
        pathsc.getString().add("pi\\\\" + piservername + "\\sinusoid");
        ArrayOfTimeSeries results = ptsc.getPISnapshotData(pathsc);

        
        if (results.getTimeSeries().get(0).getError()==0)
        {
            PIServerName = piservername;
            isConnected=1;
        }
        else
        {
        	isConnected=0;
        }
        return isConnected;
    }

    public String[] GetPIServersList()
    {
        return (new String[]{"MARC-PI2014","TEST"});
    }



	public int ValidateTagNames(String pitag1, String pitag2, String pitag3,String pitag4, String pitag5, int NumTags) {
        paths = new ArrayOfString();
        paths.getString().add("pi\\\\" + PIServerName + "\\" + pitag1);
        if (NumTags > 1)
        {
            paths.getString().add("pi\\\\" + PIServerName + "\\" + pitag2);
        }
        if (NumTags > 2)
        {
            paths.getString().add("pi\\\\" + PIServerName + "\\" + pitag3);
        }
        if (NumTags > 3)
        {
            paths.getString().add("pi\\\\" + PIServerName + "\\" + pitag4);
        }
        if (NumTags > 4)
        {
            paths.getString().add("pi\\\\" + PIServerName + "\\" + pitag5);
        }

        ArrayOfTimeSeries results = ptsc.getPISnapshotData(paths);

        int detection_error = 0;

        if (results.getTimeSeries().get(0).getError() != 0)
        {
            detection_error = 1;
        }
       
        if (NumTags > 1)
        {
        	if (results.getTimeSeries().get(1).getError() != 0)
            {
                detection_error = 1;
            }
           
        }


        if (NumTags > 2)
        {
        	if (results.getTimeSeries().get(2).getError() != 0)
            {
                detection_error = 1;
            }

        }

        if (NumTags > 3)
        {
        	if (results.getTimeSeries().get(3).getError() != 0)
            {
                detection_error = 1;
            }
        }
        if (NumTags > 4)
        {
        	if (results.getTimeSeries().get(4).getError() != 0)
            {
                detection_error = 1;
            }
        }
        return detection_error;
	}


    public List<PIValue> GetRecordedValues(int i, String starttime, String endtime)
    {
    	 ArrayOfPIArcDataRequest requests = new ArrayOfPIArcDataRequest();
    	 PIArcDataRequest request = new PIArcDataRequest();
    	
	  	  TimeRange timeRange = new TimeRange();
	      timeRange.setStart(starttime);
	      timeRange.setEnd(endtime);
	      
	      
	      PIArcManner manner = new PIArcManner();
	      manner.setBoundaries("Inside");
	      manner.setRetrievalType("Compressed");
	      request.setPIArcManner(manner);
	      request.setTimeRange(timeRange);
	      
	      if (i==1)
          {
              request.setPath(paths.getString().get(0));
          }
          else if (i==2)
          {
        	  request.setPath(paths.getString().get(1));
          }
          else if (i==3)
          {
        	  request.setPath(paths.getString().get(2));
          }
          else if (i==4)
          {
        	  request.setPath(paths.getString().get(3));
          }
          else
          {
        	  request.setPath(paths.getString().get(4));
          }
	      
	      requests.getPIArcDataRequest().add(request);
	      ArrayOfTimeSeries result = ptsc.getPIArchiveData(requests);
	      
	     
          return (ConvertToPIValues(result.getTimeSeries().get(0)));
    }

    public List<PIValue> GetInterpolatedValues(int i, String starttime, String endtime, String interval)
    {
    	
   	 ArrayOfPIArcDataRequest requests = new ArrayOfPIArcDataRequest();
   	 PIArcDataRequest request = new PIArcDataRequest();
   	 
	  	  TimeRange timeRange = new TimeRange();
	      timeRange.setStart(starttime);
	      timeRange.setEnd(endtime);
	      PIArcManner manner = new PIArcManner();
	      manner.setBoundaries("Inside");
	      manner.setRetrievalType("Interpolated");
	      int NumValues = Integer.parseInt(interval);
	      manner.setNumValues(NumValues);
	      
	      request.setPIArcManner(manner);
	      request.setTimeRange(timeRange);
	    
	      if (i==1)
          {
              request.setPath(paths.getString().get(0));
          }
          else if (i==2)
          {
        	  request.setPath(paths.getString().get(1));
          }
          else if (i==3)
          {
        	  request.setPath(paths.getString().get(2));
          }
          else if (i==4)
          {
        	  request.setPath(paths.getString().get(3));
          }
          else
          {
        	  request.setPath(paths.getString().get(4));
          }
	      
	      requests.getPIArcDataRequest().add(request);
	      ArrayOfTimeSeries result = ptsc.getPIArchiveData(requests);
	      
	     
          return (ConvertToPIValues(result.getTimeSeries().get(0)));
    }
    
    private List<PIValue> ConvertToPIValues(TimeSeries serie)
    {
    	List<PIValue> myPIValues = new ArrayList<PIValue>();
    	for (TimedValue value : serie.getTimedValues().getTimedValue()) 
    	{
    		java.util.Date myTimeStamp = value.getTime().toGregorianCalendar().getTime();
    		double myValue = Double.parseDouble(value.getValue());
    		myPIValues.add(new PIValue(myValue, myTimeStamp));
    	}
        return myPIValues;
 
    }
	
    public void Disconnect()
    {
    	
    
    }
}
