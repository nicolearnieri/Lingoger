package info.unical.View;

import info.unical.Controller.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    private String font = "Font";

    private static SceneHandler instance = null;


    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception { //metodo che puÃ² generare eccezione
        if(stage != null) return;

        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Start.fxml"));
        scene = new Scene(loader.load(), 550, 400); //v:larghezza, v1:altezza
        StartController controller= loader.getController();
        changedTheme(scene);
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
        scene.getStylesheets().add(getClass().getResource(CSS_PATH + theme + ".css").toExternalForm());
        scene.getStylesheets().add(String.valueOf(getClass().getResource(CSS_PATH + font + ".css")));

    }

    private void setCSSForAlert(Alert alert) {
        Objects.requireNonNull(alert, "Alert cannot be null");
        alert.getDialogPane().getStylesheets().clear();
        alert.getDialogPane().getStylesheets().add(getClass().getResource(CSS_PATH + theme + ".css").toExternalForm());
    }

    public void setStart() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Start.fxml"));
        scene = new Scene(loader.load(), 510, 500); //v:larghezza, v1:altezza

        StartController controller= loader.getController();
        changedTheme(scene);
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
        logInScene = new Scene(loader.load(), 510, 410); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra come modale, quindi non permette di interagire con altre finestre

        LogInController controller= loader.getController();

        changedTheme(logInScene);

        logInOrSignUpStage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        logInOrSignUpStage.getIcons().add(icon); // Imposta l'icona per la finestra

        logInOrSignUpStage.setScene(logInScene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
        controller.init();
    }


    public void setSignUp() throws Exception
    {
        if(logInOrSignUpStage!=null) {logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SignUp.fxml"));

        logInScene = new Scene(loader.load(), 510, 540); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra come modale, quindi non permette di interagire con altre finestre

        SignUpController controller= loader.getController();

        changedTheme(logInScene);

        logInOrSignUpStage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        logInOrSignUpStage.getIcons().add(icon); // Imposta l'icona per la finestra

        logInOrSignUpStage.setScene(logInScene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
        controller.init();
    }


    public void showError(String message, String title) {
        alertError.setTitle(title);
        alertError.setHeaderText("");
        alertError.setContentText(message);
        alertError.getDialogPane().setPrefWidth(500);
        alertError.showAndWait();


    }

    public void showInfo(String message, String title) {
        alertInfo.setTitle(title);
        alertInfo.setHeaderText("");
        alertInfo.setContentText(message);
        alertInfo.getDialogPane().setPrefWidth(500);
        alertInfo.show();
    }
    public boolean showConfirmation(String message, String title) {
        alertConfirmation.setTitle(title);
        alertConfirmation.setHeaderText("");
        alertConfirmation.setContentText(message);
        alertConfirmation.getDialogPane().setPrefWidth(600);

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alertConfirmation.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        ButtonType response = alertConfirmation.showAndWait().orElse(buttonTypeYes); //default se l'utente non preme ne su SI ne su NO

        return response == buttonTypeYes;
    }

    public Stage returnLogInSignUpStage()
    {
        return logInOrSignUpStage;
    }


    public void setTestChoiceMenu() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "TestChoiceMenu.fxml"));
        scene = new Scene(loader.load(), 510, 500); //v:larghezza, v1:altezza

        TestChoiceMenu controller = loader.getController();
        changedTheme(scene);

        stage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        stage.getIcons().add(icon); // Imposta l'icona per la finestra

        stage.setScene(scene);
        stage.show();
        controller.init();

    }

    public void setTestMenu(int i, String l) throws IOException {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Test.fxml"));
        scene = new Scene(loader.load(), 510, 500); //v:larghezza, v1:altezza

        TestController controller = loader.getController();
        changedTheme(scene);

        stage.setTitle("Lingoger");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        stage.getIcons().add(icon); // Imposta l'icona per la finestra

        stage.setScene(scene);
        stage.show();
        controller.initialize(i, l);
    }
}
