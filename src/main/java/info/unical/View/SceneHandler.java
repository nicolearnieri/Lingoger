package info.unical.View;

import info.unical.Controller.LogInController;
import info.unical.Controller.SignUpController;
import info.unical.Controller.StartController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class SceneHandler
{
    private final static String CSS_PATH = "/css/";
    private final static String FXML_PATH = "/SceneBuilder/";
    private final Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    private final Alert alertError = new Alert(Alert.AlertType.ERROR);
    private final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    private Scene scene;

    private Scene logInScene;
    private Stage stage;

    private Stage logInOrSignUpStage;

    private String theme = "LingogerTheme";

    private static SceneHandler instance = null;


    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception { //metodo che puÃ² generare eccezione
        if(stage != null) return;

        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Start.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza
        StartController controller= loader.getController();
        //changedTheme(scene);
        stage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        stage.getIcons().add(icon); // Imposta l'icona per la finestra
        stage.setScene(scene);
        stage.show();

    }


    public static SceneHandler getInstance() {
        if(instance == null)
            instance = new SceneHandler();
        return instance;
    }

    public void closeStage(Stage myStage)
    {
        myStage.close();
    }

    public void hideStage(Stage myStage){myStage.hide();}

    public void showStage(Stage myStage){myStage.show();}


    /*
    private void changedTheme(Scene scene) {
        setCSSForScene(scene);
        setCSSForAlert(alertError);
        setCSSForAlert(alertInfo);
        setCSSForAlert(alertConfirmation);
    }



    //aiuto
    private void setCSSForScene(Scene scene) { //in base a theme setta i css per la scena
        Objects.requireNonNull(scene);
        scene.getStylesheets().clear();
        for(String resource : resources)
            scene.getStylesheets().add(resource);

        if (font.equals("FontMontserrat")) {
            loadMontserrat();
        } else {
            loadOpenDyslexic();
        }
    }

    */

    public void setStartInterface() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Start.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza

        StartController controller= loader.getController();
        //changedTheme(scene);
        stage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        stage.getIcons().add(icon); // Imposta l'icona per la finestra
        stage.setScene(scene);
        stage.show();
    }


    public void setLogIn() throws Exception {
        if(logInOrSignUpStage!=null) {logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "LogIn.fxml"));
        logInScene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra come modale, quindi non permette di interagire con altre finestre

        LogInController controller= loader.getController();

        //changedTheme(logInScene);

        logInOrSignUpStage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        logInOrSignUpStage.getIcons().add(icon); // Imposta l'icona per la finestra

        logInOrSignUpStage.setScene(logInScene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
    }


    public void setSignUp() throws Exception
    {
        if(logInOrSignUpStage!=null) {logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SignUp.fxml"));

        logInScene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra come modale, quindi non permette di interagire con altre finestre

        SignUpController controller= loader.getController();

        //changedTheme(logInScene);

        logInOrSignUpStage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        logInOrSignUpStage.getIcons().add(icon); // Imposta l'icona per la finestra

        logInOrSignUpStage.setScene(logInScene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
    }



}
