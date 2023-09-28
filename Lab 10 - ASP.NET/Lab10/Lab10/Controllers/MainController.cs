using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Lab10.Models;
using Lab10.DataAbstractionLayer;

namespace Lab10.Controllers
{
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult Index()
        {
            return View("FilterLogs");
        }
        public string Test()
        {
            return "It's working";
        }

        public string GetLogsFromUser()
        {
            string theUser = Request.Params["user_id"].ToString();
            DAL dal = new DAL();
            List<Log> logList = dal.GetLogsForUser(theUser);
            ViewData["logList"] = logList;

            string res = "<table><thead><th>Id</th><th>Type</th><th>Severity</th><th>Date</th><th>User</th><th>Text</th></thead>";


            foreach (Log eachLog in logList)
            {
                res += "<tr><td>" + eachLog.Id + "</td><td>" + eachLog.Type + "</td><td>" + eachLog.Severity + "</td><td>" + eachLog.Date + "</td>" +
                    "<td></td>" + eachLog.User + "</td><td>" + eachLog.Text + "</td></tr>";
            }

            res += "</table>";
            return res;


        }


    }
}