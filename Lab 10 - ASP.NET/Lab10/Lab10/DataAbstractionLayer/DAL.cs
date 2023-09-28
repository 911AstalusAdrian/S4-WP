using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Lab10.Models;
using MySql.Data.MySqlClient;

namespace Lab10.DataAbstractionLayer
{
    public class DAL
    {
        public List<Log> GetLogsForUser(string theUser)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=wplabdb;";
            List<Log> llist = new List<Log>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from logreports where logUser=" + theUser;
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Log aLog = new Log();
                    aLog.Id = myreader.GetInt32("logID");
                    aLog.Type = myreader.GetString("logType");
                    aLog.Severity = myreader.GetString("logSeverity");
                    aLog.Date = myreader.GetString("logDate");
                    aLog.User = myreader.GetString("logUser");
                    aLog.Text = myreader.GetString("logText");
                    llist.Add(aLog);
                }
                myreader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return llist;
        }
    }
}