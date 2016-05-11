using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace SampleApp_RDotNet_VS2012
{
    public class PIValues : CollectionBase
    {
        public void Add(PIValue newPIValue)
        {
            List.Add(newPIValue);
        }
        public void Remove(PIValue oldPIValue)
        {
            List.Remove(oldPIValue);
        }
        public PIValues()
        {
        }
        public PIValue this[int PIValueIndex]
        {
            get
            {
                return (PIValue)List[PIValueIndex];
            }
            set
            {
                List[PIValueIndex] = value;
            }
        }
    }
}
