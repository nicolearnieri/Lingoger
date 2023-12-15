package info.unical.Controller;

import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button logInButton;

    @FXML
    private Button signUpButton;

    @FXML
    void logInClicked(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setLogIn();

    }

    @FXML
    void signUpClicked(ActionEvent event) {
        SceneHandler.getInstance().setSignUp();

    }

}
