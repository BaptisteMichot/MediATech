using MediATech.Model.DAL;
using MediATech.Model.DAL.User;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model
{
    public class PrimaryModel : IModel
    {
        DAOFactory factory = new DAOFactory();

        private IUserDAO iUserDao;

        public PrimaryModel()
        {
            this.iUserDao = factory.CreateUserDAO();
        }

        public bool Register(string firstName, string lastName, string email, string password)
        {
            return this.iUserDao.Register(firstName, lastName, email, password);
        }

        public bool Login(string email, string password)
        {
            return this.iUserDao.Login(email, password);
        }
    }
}
