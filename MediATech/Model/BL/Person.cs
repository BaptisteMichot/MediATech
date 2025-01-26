using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public abstract class Person : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler? PropertyChanged;

        private int _id;
        private string _firstName;
        private string _lastName;
        private string _email;
        private string _login;
        private string _password;

        
        public Person(int id, string firstName, string lastName, string email, string login, string password)
        {
            Id = id;
            LastName = lastName;
            FirstName = firstName;
            Email = email;
            Login = login;
            Password = password;
        }

        public int Id { get => _id; set => _id = value; }

        public string FirstName
        {
            get => _firstName;
            set
            {
                _firstName = value;
                OnPropertyChanged(nameof(FirstName));
            }
        }

        public string LastName
        {
            get => _lastName;
            set
            {
                _lastName = value;
                OnPropertyChanged(nameof(LastName));
            }
        }

        public string Email
        {
            get => _email;
            set
            {
                _email = value;
                OnPropertyChanged(nameof(Email));
            }
        }

        public string Login
        {
            get => _login;
            private set
            {
                _login = value;
                OnPropertyChanged(nameof(Login));
            }
        }

        protected string Password
        {
            get => _password;
            set
            {
                _password = value;
                OnPropertyChanged(nameof(Password));
            }
        }


        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

    }
}
