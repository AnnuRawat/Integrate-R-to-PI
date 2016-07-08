
public interface IPIDeveloperTech
{
    String GetVersion();
    Boolean Connect(String PIDataArchiveName);
    String[] GetPIDataArchiveNamesList();
    Boolean ValidateTagNames(String[] piPoints);
    PIValuesList GetRecordedValues(String startTime, String endTime);
    PIValuesList GetInterpolatedValues(String startTime, String endTime, String interval);
    void Disconnect();

    String GetServerName();
}