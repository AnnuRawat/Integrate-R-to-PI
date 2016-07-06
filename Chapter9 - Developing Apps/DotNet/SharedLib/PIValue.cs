using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SharedLib
{
    public class PIValue
    {
        public string Status{ get; set; }
        public DateTime Timestamp{ get; set; }
        public string UnitOfMeasure{ get; set; }
        public double Value{ get; set; }

        public PIValue()
        { 
        
        }
        public PIValue(double myValue)
        {
            Value = myValue;
            Timestamp = DateTime.Now;
        }

        public PIValue(double myValue, DateTime timestamp)
        {
            Value = myValue;
            Timestamp = timestamp;
        }
    }
}
