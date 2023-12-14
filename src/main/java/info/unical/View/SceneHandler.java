package info.unical.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

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

    private SceneHandler()
    {}

    public void init(Stage primaryStage) throws Exception { //metodo che puÃ² generare eccezione
        if(stage != null) return;

        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Start.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza
        StartController controller= loader.getController();
        changedTheme(scene);
        stage.setTitle("DeliverBoo");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Isabelle.png")));// Carica l'immagine dell'icona
        stage.getIcons().add(icon); // Imposta l'icona per la finestra
        stage.setScene(scene);
        stage.show();

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
        for(String resource : resources)
            scene.getStylesheets().add(resource);

        if (font.equals("FontMontserrat")) {
            loadMontserrat();
        } else {
            loadOpenDyslexic();
        }
    }



}
