using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace MediATech.ViewModel
{
    public partial class MainPageViewModel : ObservableObject
    {
        [RelayCommand]
        private async Task ClickOnLogin()
        {
            await Shell.Current.GoToAsync("LoginPage");
        }

        [RelayCommand]
        private async Task ClickOnRegister()
        {
            await Shell.Current.GoToAsync("RegisterPage");
        }
    }
}
