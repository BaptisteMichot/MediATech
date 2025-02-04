using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using MediATech.Model.DAL.User;
using MediATech.Model.DAL;
using MediATech.Model.BL;
using MediATech.Model;

namespace MediATech.ViewModel
{
    public partial class LoginPageViewModel : ObservableObject
    {
        private IModel model;
        
        [ObservableProperty]
        private string _email;

        [ObservableProperty]
        private string _password;

        [ObservableProperty]
        private string _firstName;

        [ObservableProperty]
        private string _lastName;

        [ObservableProperty]
        private bool _isWelcomePageVisible = true;

        [ObservableProperty]
        private bool _isUsernamePageVisible = false;

        [ObservableProperty]
        private bool _isPasswordPageVisible = false;

        [ObservableProperty]
        private bool _isRegisterPageVisible = false;

        [ObservableProperty]
        private bool _isLoginFailedVisible = false;

        [ObservableProperty]
        private bool _isRegisterSucceedPageVisible = false;

        public LoginPageViewModel()
        {
            model = new PrimaryModel();
        }

        [RelayCommand]
        private async Task ClickOnLogin()
        {
            IsWelcomePageVisible = false;
            IsRegisterSucceedPageVisible = false;
            IsUsernamePageVisible = true;            
        }

        [RelayCommand]
        private async Task ClickOnRegister()
        {
            IsWelcomePageVisible = false;
            IsRegisterPageVisible = true;
        }

        [RelayCommand]
        private async Task ClickOnCancel()
        {
            IsWelcomePageVisible = true;
            IsUsernamePageVisible = false;            
            IsPasswordPageVisible = false;
            IsRegisterPageVisible = false;
            IsLoginFailedVisible = false;
            Email = "";
            Password = "";
        }

        [RelayCommand]
        private async Task ClickOnNextAfterUsername()
        {
            IsUsernamePageVisible = false;
            IsPasswordPageVisible = true;
        }

        [RelayCommand]
        private async Task ClickOnNextAfterPassword()
        {
            bool isAuthenticated = model.Login(Email, Password);

            if (isAuthenticated)
            {
                await Shell.Current.GoToAsync("//BookingPage");
            }
            else
            {
                IsLoginFailedVisible = true;
            }
        }

        [RelayCommand]
        private async Task ClickOnValidate()
        {
            bool isRegistered = model.Register(FirstName, LastName, Email, Password);

            if (isRegistered) {
                IsRegisterSucceedPageVisible = true;
                IsRegisterPageVisible = false;
            }            
        }
    }
}
