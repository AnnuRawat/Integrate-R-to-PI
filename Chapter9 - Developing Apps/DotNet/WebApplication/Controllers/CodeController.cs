using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web.Http;
using RDotNet;
using RDotNet.Graphics;
using Svg;
using SharedLib;
using WebApplication.Models;


namespace WebApplication.Controllers
{

    public class CodeController : ApiController
    {

        public CodeController()
        {

        }

        [HttpPost]
        public IHttpActionResult Execute()
        {
            REngine _engine = REngine.GetInstance(null, true, null, null);
            SvgGraphicsDevice GraphicsDevice = new SvgGraphicsDevice(new SvgContextMapper(700, 700, SvgUnitType.Pixel, null));
            _engine.Initialize();
            _engine.Install(GraphicsDevice);
            RApplication app = new RApplication(_engine);
            app.DataAccessMethod = "PIAFSDK";
            app.Connect("MARC-PI2016");
            app.RFunction = "PI Histogram";
            app.NumTags = 1;
            app.Mode = "Interpolated";
            var tag1 = "FAC.OAK.Weather-Outside_Temperature-Val.PV";
            var tag2 = "FAC.OAK.Weather-Inside_Temperature-Val.PV";
            var tag3 = "FAC.OAK.Weather-Inside_Humidity-Val.PV";
            var tag4 = "FAC.OAK.Weather-Outside_Humidity-Val.Pv";
            var tag5 = "FAC.OAK.Power-Total_Demand_Calc.PV";

            app.GetPIData(tag1, tag2, tag3, tag4, tag5, "1-Oct-2012", "1-Nov-2012", "1h");
            app.GenerateGhaphic("5", "0", "1", "");

            //GraphicsDevice.ClearImages();
            //var evaluated = statements.Select(_engine.Evaluate).ToList();
            var plots = GraphicsDevice.GetImages().Select(RenderSvg).ToList();
            return Ok(plots);

        }

        private static string RenderSvg(SvgDocument image)
        {
            using (var stream = new MemoryStream())
            {
                image.Write(stream);
                stream.Position = 0;
                using (var reader = new StreamReader(stream))
                {
                    var contents = reader.ReadToEnd();
                    return contents;
                }
            }
        }

    }
}
