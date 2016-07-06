using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SampleApp_RDotNet_VS2012
{
    public interface IPIDeveloperTech
    {
        string GetVersion();
        bool Connect(string PIDataArchiveName = null);
        string[] GetPIDataArchiveNamesList();
        bool ValidateTagNames(string[] piPoints);
        PIValuesList GetRecordedValues(string startTime, string endTime);
        PIValuesList GetInterpolatedValues(string startTime, string endTime, string interval);
        void Disconnect();

        string GetServerName();
    }
}
