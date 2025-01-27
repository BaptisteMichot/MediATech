using Npgsql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.DAL.User
{
    public class UserDAO
    {
        private NpgsqlConnection connection;

        public UserDAO(NpgsqlConnection connection)
        {
            try
            {
                this.connection = connection;


            } catch (Exception ex)
            {
                Console.WriteLine(ex.StackTrace);
            }
        }

        
    }
}
