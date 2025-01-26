using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace MediATech.ViewModel
{
    public partial class LoginPageViewModel : ObservableObject
    {
        [RelayCommand]
        private async Task ClickOnCancel()
        {
            await Shell.Current.GoToAsync("MainPage");
        }

        [RelayCommand]
        private async Task ClickOnNext()
        {
            await Shell.Current.GoToAsync("PasswordPage");
        }
    }
}
