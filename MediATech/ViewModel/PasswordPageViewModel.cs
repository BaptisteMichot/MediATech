using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace MediATech.ViewModel
{
    public partial class PasswordPageViewModel : ObservableObject
    {
        [RelayCommand]
        private async Task ClickOnCancel()
        {
            await Shell.Current.GoToAsync("//MainPage");
        }

        [RelayCommand]
        private async Task ClickOnNext()
        {
            await Shell.Current.GoToAsync("//BookingPage");
        }
    }
}
