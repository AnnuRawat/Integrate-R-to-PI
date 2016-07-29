using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RMultWebService.Models
{
    public class QueryData
    {
        public int Width { get; set; }
        public int Height { get; set; }
        public string StartTime { get; set; }
        public string EndTime { get; set; }
        public string Interval { get; set; }
        public string[] Paths { get; set; }

        public QueryData()
        {

        }
    }
}