using Microsoft.Extensions.Logging;
using MediATech.View;
using MediATech.ViewModel;

namespace MediATech
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                    fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
                });

            //permet de faire une injection de dépendance dans le constructeur de la MainPage sans devoir faire un new MainPageViewModel()
            builder.Services.AddTransient<MainPageViewModel>();
            builder.Services.AddTransient<MainPage>();            

            builder.Services.AddTransient<LoginPageViewModel>();
            builder.Services.AddTransient<LoginPage>();

            builder.Services.AddTransient<RegisterPageViewModel>();
            builder.Services.AddTransient<RegisterPage>();

            builder.Services.AddTransient<PasswordPageViewModel>();
            builder.Services.AddTransient<PasswordPage>();

            builder.Services.AddTransient<BookingPageViewModel>();
            builder.Services.AddTransient<BookingPage>();


#if DEBUG
            builder.Logging.AddDebug();
#endif

            return builder.Build();
        }
    }
}
