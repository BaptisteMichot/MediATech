using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.DAL.User
{
    public interface IUserDAO
    {
        public bool Login(string email, string password);

        public bool Register(string firstName, string lastName, string email, string password);
    }
}
