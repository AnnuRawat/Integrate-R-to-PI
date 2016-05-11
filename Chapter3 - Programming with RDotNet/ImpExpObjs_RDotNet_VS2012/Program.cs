using RDotNet;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImpExpObjs_RDotNet_VS2012
{
    class Program
    {
        static void Main(string[] args)
        {
            
            //Create R.NET instance
            REngine engine = REngine.CreateInstance("RDotNet");
            engine.Initialize();

            Console.WriteLine("Is engine running? " + engine.IsRunning);
            Console.WriteLine("Engine ID: " + engine.ID);
            ExportingRDotNetObjs.Start(engine);
            ImportingRDotNetObjs.Start(engine);
            

            //Close R.NET connection
            //engine.Close();

            Console.WriteLine("\nFinished");
            Console.ReadKey();
        }
    }
}
