using Npgsql;
using System;
using System.Threading.Tasks;

namespace MediATech.Model.DAL.User
{
    public class UserDAO : IUserDAO
    {
        private NpgsqlConnection connection;

        string getLogin = "SELECT email, password FROM users WHERE email = @email AND password = @password";


        public UserDAO(NpgsqlConnection connection)
        {
            try
            {
                this.connection = connection;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.StackTrace);
            }
        }

        public bool Login(string email, string password)
        {
            try
            {
                using var cmd = new NpgsqlCommand(getLogin, connection);
                cmd.Parameters.AddWithValue("@email", email);
                cmd.Parameters.AddWithValue("@password", password);

                using var reader = cmd.ExecuteReader();
                bool exists = reader.HasRows;

                return exists;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erreur de connexion : " + ex.Message);
                return false;
            }
        }
    }
}
