using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab10.Models
{
    public class Log
    {
        public int Id { get; set; }
        public string Type { get; set; }
        public string Severity { get; set; }
        public string Date { get; set; }
        public string User { get; set; }
        public string Text { get; set; }
    }
}