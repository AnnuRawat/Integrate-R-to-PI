using OSIsoft.AF.Asset;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SharedLib
{
    public class PIValuesList : List<PIValues>
    {
        public PIValuesList()
        {

        }

        public PIValuesList(IEnumerable<AFValues> valuesList)
        {
            foreach (AFValues values in valuesList)
            {
                PIValues vals = ConvertToPIValues(values);
                this.Add(vals);
            }
        }

        public PIValuesList(dynamic valuesList)
        {
            foreach (dynamic values in valuesList.Items)
            {
                PIValues vals = ConvertToPIValues(values);
                this.Add(vals);
            }
        }

        private PIValues ConvertToPIValues(dynamic values)
        {
            PIValues piValues = new PIValues();
            foreach (var value in values.Items)
            {
                try
                {
                    double dValue = Convert.ToDouble(value.Value);
                    DateTime dt = Convert.ToDateTime(value.Timestamp);
                    piValues.Add(new PIValue(dValue, dt));
                }
                catch (Exception ex)
                {
                    Debug.WriteLine(ex.Message);
                }

            }
            piValues.PIPointName = values.Name;
            return piValues;
        }

        private PIValues ConvertToPIValues(AFValues values)
        {
            PIValues piValues = new PIValues();
            foreach (var value in values)
            {
                try
                {
                    double dValue = value.ValueAsDouble();
                    piValues.Add(new PIValue(dValue, value.Timestamp.LocalTime));
                }
                catch (Exception ex)
                {

                }

            }
            piValues.PIPointName = values.PIPoint.Name;
            return piValues;
        }
    }
}
