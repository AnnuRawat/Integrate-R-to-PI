using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SampleApp_RDotNet_VS2012
{
    public class PIValue
    {
        public string Status{ get; set; }
        public DateTime Timestamp{ get; set; }
        public string UnitOfMeasure{ get; set; }
        public Object Value{ get; set; }

        public PIValue()
        { 
        
        }
        public PIValue(Object myValue)
        {
            Value = myValue;
            Timestamp = DateTime.Now;
        }

        public PIValue(Object myValue, DateTime timestamp)
        {
            Value = myValue;
            Timestamp = timestamp;
        }
    }
}
