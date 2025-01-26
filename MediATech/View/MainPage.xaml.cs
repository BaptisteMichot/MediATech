using MediATech.ViewModel;

namespace MediATech.View
{
    public partial class MainPage : ContentPage
    {
        public MainPage(MainPageViewModel mainPageVM)
        {
            InitializeComponent();
            BindingContext = mainPageVM;
        }        
    }
}
