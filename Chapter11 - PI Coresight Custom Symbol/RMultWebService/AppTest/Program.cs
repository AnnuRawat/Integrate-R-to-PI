using RDotNet;
using RMultWebService;
using RMultWebService.Models;
using Svg;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RDotNet;
using RDotNet.Graphics;



namespace AppTest
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] paths = new string[] { @"af:\\MARC-PI2016\IntegratingPISystemAndR\MainElement|Inside Humidity",
@"af:\\MARC-PI2016\IntegratingPISystemAndR\MainElement|Inside Temperature" ,
@"af:\\MARC-PI2016\IntegratingPISystemAndR\MainElement|OutsideTemperature"  };
            REngine _engine = REngine.GetInstance(null, true, null, null);
            SvgGraphicsDevice GraphicsDevice = new SvgGraphicsDevice(new SvgContextMapper(700, 700, SvgUnitType.Pixel, null));
            _engine.Initialize();
            _engine.Install(GraphicsDevice);
  
            RApplication app = new RApplication();
            app.GetPIData(paths, "1-Oct-2012", "1-Nov-2012", "1h");
            app.GenerateGraphic(_engine);
        }
    }
}
