using MediATech.ViewModel;

namespace MediATech.View;

public partial class LoginPage : ContentPage
{
	public LoginPage(LoginPageViewModel loginPageVM)
	{
		InitializeComponent();
        BindingContext = loginPageVM;
    }
}