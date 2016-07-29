using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web.Http;
using RDotNet;
using RDotNet.Graphics;
using Svg;
using RMultWebService.Models;
using System.Web.Http.Cors;
using System.Web;
using System.Drawing.Imaging;
using System.Diagnostics;
using System.Configuration;



namespace RMultWebService.Controllers
{
    public class CodeController : ApiController
    {
        private static REngine _engine = null;
        private static SvgGraphicsDevice GraphicsDevice = null;
        private static int lastWidth = -1;
        private static readonly object _object = new object();

        public CodeController()
        {
            if (_engine != null)
            {
                return;
            }
            {
                _engine = REngine.GetInstance(null, true, null, null);
                _engine.Initialize();          
                string rFilePath = ConfigurationManager.AppSettings["rFunctionPath"];
                _engine.Evaluate("source(\"" + rFilePath + "\")");
                GraphicsDevice = new SvgGraphicsDevice(new SvgContextMapper(400, 400, SvgUnitType.Pixel, null));
                _engine.Install(GraphicsDevice);
            }
        }

        [HttpPost]
        public IHttpActionResult Execute(QueryData queryData)
        {
            Stopwatch watch = new Stopwatch();
            watch.Start();
          
            try
            {
                if ((queryData.Paths == null) || (queryData.Paths.Count() < 2))
                {
                    throw new Exception("There should be at least 2 attributes within the symbol.");
                }
                if (queryData.Paths.All(m => m.Substring(0, 2) == "af") == false)
                {
                    throw new Exception("PI Points are not accepted");
                }
                IEnumerable<string> plots = null;
                RApplication app = new RApplication();
                app.GetPIData(queryData.Paths, queryData.StartTime, queryData.EndTime, queryData.Interval);

                lock (_object)
                {
                    System.Threading.Thread.Sleep(1000);
                    if (lastWidth != queryData.Width)
                    {
                        GraphicsDevice = new SvgGraphicsDevice(new SvgContextMapper(queryData.Width, queryData.Height, SvgUnitType.Pixel, null));
                        _engine.Install(GraphicsDevice);
                        lastWidth = queryData.Width;
                    }                 

                    app.GenerateGraphic(_engine);
                    plots = GraphicsDevice.GetImages().Select(RenderSvg);
                }
                return Ok(plots);

            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message + "|\n" + ex.Source + "|\n" + ex.StackTrace);
            }
            finally
            {

                watch.Stop();
            }

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
