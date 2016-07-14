import java.util.Date;

public class PIValue {
	private int iStatus;
	private double iValue;
	private Date iTimeStamp;
	
	public PIValue(double Value, Date TimeStamp, int Status)
	{
		iStatus=Status;
		iValue=Value;
		iTimeStamp=TimeStamp;
	}
	
	public PIValue()
	{

	}
	
	public PIValue(Object Value, Object TimeStamp)
	{
		iValue=(double)Value;
		iTimeStamp = (Date)TimeStamp;
	}
	
	public double GetValue()
	{
		return (iValue);
	}
	public Date GetTimeStamp()
	{
		return (iTimeStamp);
	}
	public double GetUTCTimeStamp()
	{
		return iTimeStamp.getTime();
	}
	
	public int GetStatus()
	{
		return (iStatus);
	}
	
	public void SetValue(double Value)
	{
		iValue=Value;
	}
	public void SetTimeStamp(Date TimeStamp)
	{
		iTimeStamp=TimeStamp;
	}
	
	public void SetStatus(int Status)
	{
		iStatus=Status;
	}
	
}
