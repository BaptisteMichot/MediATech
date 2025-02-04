using Npgsql;
using System;
using System.Threading.Tasks;

namespace MediATech.Model.DAL.User
{
    public class UserDAO : IUserDAO
    {
        private NpgsqlConnection connection;

        const string defaultRole = "user";

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
                string getLoginQuery = "SELECT password FROM users WHERE email = @email";

                using var cmd = new NpgsqlCommand(getLoginQuery, connection);
                cmd.Parameters.AddWithValue("@email", email);

                using var reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    string storedHash = reader.GetString(0);
                    return BCrypt.Net.BCrypt.Verify(password, storedHash);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erreur de connexion : " + ex.Message);
            }
            return false;
        }


        public bool Register(string firstName, string lastName, string email, string password)
        {
            try
            {
                string hashedPassword = BCrypt.Net.BCrypt.HashPassword(password);

                string insertUser = "INSERT INTO users (firstName, lastName, email, password, role) " +
                    "VALUES (@firstName, @lastName, @email, @password, @role)";

                using var cmd = new NpgsqlCommand(insertUser, connection);
                cmd.Parameters.AddWithValue("@firstName", firstName);
                cmd.Parameters.AddWithValue("@lastName", lastName);
                cmd.Parameters.AddWithValue("@email", email);
                cmd.Parameters.AddWithValue("@password", hashedPassword);
                cmd.Parameters.AddWithValue("@role", defaultRole);

                int rowsAffected = cmd.ExecuteNonQuery();
                return rowsAffected > 0;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erreur d'inscription : " + ex.Message);
                return false;
            }
        }
    }
}
