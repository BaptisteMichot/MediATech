using System;
using MediATech.Model.DAL.User;
using Npgsql;


namespace MediATech.Model.DAL
{
    public class DAOFactory
    {
        private NpgsqlConnection connection;
        private const string url = "Host=127.0.0.1;Database=mediatechdb;Username=postgres;Password=Q47!pL9s$2kT";

        public DAOFactory()
        {
            try
            {
                connection = new NpgsqlConnection(url);
                connection.Open();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        public static NpgsqlConnection GetConnection()
        {
            var connection = new NpgsqlConnection("Host=127.0.0.1;Database=mediatechdb;Username=postgres;Password=Q47!pL9s$2kT");
            connection.Open();
            return connection;
        }

        public bool Close()
        {
            if (connection != null)
            {
                try
                {
                    connection.Close();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                    return false;
                }
            }

            return true;
        }

        public IUserDAO CreateUserDAO()
        {
            return new UserDAO(this.connection);
        }
    }
}
