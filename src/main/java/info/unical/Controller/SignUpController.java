package info.unical.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignUpController {

    @FXML
    private TextField confirmPVisible;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailLabel;

    @FXML
    private ComboBox<String> languageChooser;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField seePField;

    @FXML
    private Label signUpClickableLabel;

    @FXML
    private TextField usernameLabel;

    static ObservableList<String> languageList = FXCollections.observableArrayList("Inglese","Francese", "Spagnolo", "Portoghese");


   public void init()
   {
       languageChooser.setItems(languageList);

   }
}
