using MediATech.ViewModel;

namespace MediATech.View;

public partial class BookingPage : ContentPage
{
	public BookingPage(BookingPageViewModel bookingPageVM)
	{
		InitializeComponent();
		BindingContext = bookingPageVM;
	}
}