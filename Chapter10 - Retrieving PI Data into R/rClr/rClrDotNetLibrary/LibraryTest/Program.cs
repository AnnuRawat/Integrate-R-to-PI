using rClrDotNetLibrary;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LibraryTest
{
    class Program
    {
        static void Main(string[] args)
        {

            PISystemWrapper piSystemWrapper = new PISystemWrapper();
            piSystemWrapper.GetPIData("marc-pi2016", "FAC.OAK.Weather-Inside_Humidity-Val.PV", "FAC.OAK.Weather-Inside_Temperature-Val.PV", "FAC.OAK.Weather-Outside_Temperature-Val.PV", "1-Oct-2012", "1-Nov-2012", "1h");
            double[] values1 = piSystemWrapper.GetValues(1);
            double[] values2 = piSystemWrapper.GetValues(2);
            double[] values3 = piSystemWrapper.GetValues(3);
        }
    }
}
