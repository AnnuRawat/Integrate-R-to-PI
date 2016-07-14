using OSIsoft.AF.Asset;
using OSIsoft.AF.PI;
using OSIsoft.AF.Time;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace rClrDotNetLibrary
{
    public class PISystemWrapper
    {
        private double[] values1 = null;
        private double[] values2 = null;
        private double[] values3 = null;
        public void GetPIData(string piDataArchiveName, string piPointName1, string piPointName2, string piPointName3, string startTime, string endTime, string interval)
        {
            IEnumerable<string> piPointNames = new List<string> { piPointName1, piPointName2, piPointName3 };
            PIServer piServer = new PIServers()[piDataArchiveName];
            if (piServer == null)
            {
                return;
            }
            IList<PIPoint> points = PIPoint.FindPIPoints(piServer, piPointNames);
            PIPointList pointList = new PIPointList(points);
            AFTimeRange timeRange = new AFTimeRange(new AFTime(startTime), new AFTime(endTime));
            AFTimeSpan timeSpan;
            bool result = AFTimeSpan.TryParse(interval, out timeSpan);
            if (result == false)
            {
                AFTimeSpan.TryParse("1h", out timeSpan);
            }
            IEnumerable<AFValues> valuesList = pointList.InterpolatedValues(timeRange, timeSpan, string.Empty, false, new PIPagingConfiguration(PIPageType.TagCount, 100));
            foreach(AFValues values in valuesList)
            {
                if (values.PIPoint.Name == piPointName1)
                {
                    values1 = values.Where(m => m.ValueTypeCode == TypeCode.Single || m.ValueTypeCode == TypeCode.Double).Select(m => m.ValueAsDouble()).ToArray();
                }
                else if (values.PIPoint.Name == piPointName2)
                {
                    values2 = values.Where(m => m.ValueTypeCode == TypeCode.Single || m.ValueTypeCode == TypeCode.Double).Select(m => m.ValueAsDouble()).ToArray();
                }
                else if (values.PIPoint.Name == piPointName3)
                {
                    values3 = values.Where(m => m.ValueTypeCode == TypeCode.Single || m.ValueTypeCode == TypeCode.Double).Select(m => m.ValueAsDouble()).ToArray();
                }
            }
        }

        public double[] GetValues(int i)
        {
            if (i==1)
            {
                return values1;
            }
            else if (i == 2)
            {
                return values2;
            }
            else if (i == 3)
            {
                return values3;
            }
            else
            {
                return null;
            }
        }
    }
}
