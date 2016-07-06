using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OSIsoft.AF;
using OSIsoft.AF.Data;
using OSIsoft.AF.Asset;
using OSIsoft.AF.PI;
using OSIsoft.AF.Time;

namespace SharedLib
{
    public class PIAFSDK_Wrapper : IPIDeveloperTech
    {
        private PIServers pIServers = null;
        private PIServer piServer = null;
        private PIPointList pointList = null;


        public PIAFSDK_Wrapper()
        {
            pIServers = new PIServers();
        }


        public string GetVersion()
        {
            return (new PISystems().Version);
        }

        public string GetServerName()
        {

            if (piServer == null)
            {
                return "None";

            }
            else
            {
                return piServer.Name;
            }

        }

        public bool Connect(string PIDataArchiveName = null)
        {
            try
            {

                if (PIDataArchiveName == null)
                {
                    piServer = pIServers.DefaultPIServer;
                }
                else
                {
                    piServer = pIServers[PIDataArchiveName];

                }
                piServer.Connect();
                return true;
            }
            catch
            {
                return false;
            }

        }

        public string[] GetPIDataArchiveNamesList()
        {
            return pIServers.Select(m => m.Name).ToArray();
        }


        public bool ValidateTagNames(string[] piPoints)
        {
            try
            {
                IList<PIPoint> piPointList = PIPoint.FindPIPoints(piServer, piPoints);
                pointList = new PIPointList(piPointList);
                return true;

            }
            catch
            {
                return false;
            }
        }

        public PIValuesList GetRecordedValues(string startTime, string endTime)
        {
            AFTime start = new AFTime(startTime);
            AFTime end = new AFTime(endTime);
            AFTimeRange timeRange = new AFTimeRange(start, end);
            IEnumerable<AFValues> valueResults = pointList.RecordedValues(timeRange, AFBoundaryType.Inside, string.Empty, false, new PIPagingConfiguration(PIPageType.TagCount, 100));
            return new PIValuesList(valueResults);
        }

        public PIValuesList GetInterpolatedValues(string startTime, string endTime, string interval)
        {
            AFTime start = new AFTime(startTime);
            AFTime end = new AFTime(endTime);
            AFTimeRange timeRange = new AFTimeRange(start, end);
            AFTimeSpan timeSpan = AFTimeSpan.Parse(interval);
            IEnumerable<AFValues> valueResults = pointList.InterpolatedValues(timeRange, timeSpan, string.Empty, false, new PIPagingConfiguration(PIPageType.TagCount, 100));
            return new PIValuesList(valueResults);
        }

        public void Disconnect()
        {
            if (piServer != null)
            {
                piServer.Disconnect();
            }
        }

    }
}
