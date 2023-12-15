package info.unical.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class LogInController {

    @FXML
    private Button logButton;

    @FXML
    private ImageView image;


    @FXML
    private PasswordField passwordHiddenField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label registrationCLabel;

    @FXML
    private TextField usernameOrEmailField;

    Image openEye = new Image(Objects.requireNonNull(getClass().getResource("/images/open_eye.png")).toExternalForm());
    Image eyeOff = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_off.png")).toExternalForm());



    public void initialize() {

        passwordFields();

    }


    private void passwordFields()
    {
        passwordField.textProperty().bindBidirectional(passwordHiddenField.textProperty());
        image.setImage(openEye);

        image.setOnMouseClicked(event -> {
            if (image.getImage() == openEye)
            {
                image.setImage(eyeOff);
                passwordHiddenField.setVisible(false);
                passwordField.setVisible(true);
            }
            else
            {
                image.setImage(openEye);
                passwordHiddenField.setVisible(true);
                passwordField.setVisible(false);
            }
        });
    }

}
