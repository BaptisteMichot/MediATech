package mediatech;

import javafx.application.Application;
import javafx.stage.Stage;
import mediatech.View.LoginView;

public class App  extends Application {
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new LoginView(stage);
        stage.show();
    }
}
