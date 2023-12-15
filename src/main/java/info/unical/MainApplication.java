package info.unical;

import info.unical.View.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;

public class MainApplication extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            SceneHandler.getInstance().init(primaryStage);

        } catch(Exception e) {
            e.printStackTrace(); //stampa il tracciamento delle chiamate che ha portato all'errore
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
