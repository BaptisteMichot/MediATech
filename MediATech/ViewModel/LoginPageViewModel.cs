using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using MediATech.Model.DAL.User;
using MediATech.Model.DAL;

namespace MediATech.ViewModel
{
    public partial class LoginPageViewModel : ObservableObject
    {
        private UserDAO user;

        [ObservableProperty]
        private string _email;

        [ObservableProperty]
        private string _password;

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
            var connection = DAOFactory.GetConnection();
            user = new UserDAO(connection);
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
            bool isAuthenticated = user.Login(Email, Password);

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
            IsRegisterSucceedPageVisible = true;
            IsRegisterPageVisible = false;
        }
    }
}
