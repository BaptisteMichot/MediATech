using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public class Employee : Person
    {
        public Employee(int id, string firstName, string lastName, string email, string login, string password)
            : base (id, firstName, lastName, email, login, password)
        {

        }
    }
}
