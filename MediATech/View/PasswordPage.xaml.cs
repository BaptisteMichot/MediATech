using MediATech.ViewModel;

namespace MediATech.View;

public partial class PasswordPage : ContentPage
{
	public PasswordPage(PasswordPageViewModel passwordPageVM)
	{
		InitializeComponent();
		BindingContext = passwordPageVM;
	}
}