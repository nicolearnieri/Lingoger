package info.unical.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class SignUpController {

    @FXML
    private TextField confirmPVisible;

    @FXML
    private ImageView image;


    @FXML
    private ImageView cImage;

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

    Image openEye = new Image(Objects.requireNonNull(getClass().getResource("/images/open_eye.png")).toExternalForm());
    Image eyeOff = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_off.png")).toExternalForm());



    public void init()
   {
       languageChooser.setItems(languageList);
       passwordFields();

   }

   public void passwordFields()
   {
         passwordField.textProperty().bindBidirectional(seePField.textProperty());
         confirmPasswordField.textProperty().bindBidirectional(confirmPVisible.textProperty());
         image.setImage(openEye); //password
         cImage.setImage(openEye); //confirmation password

         image.setOnMouseClicked(event -> {
              if (image.getImage() == openEye)
              {
                image.setImage(eyeOff);
                passwordField.setVisible(false);
                seePField.setVisible(true);
              }
              else
              {
                image.setImage(openEye);
                seePField.setVisible(false);
                passwordField.setVisible(true);
              }
         });

         cImage.setOnMouseClicked(event -> {
              if (cImage.getImage() == openEye)
              {
                cImage.setImage(eyeOff);
                confirmPasswordField.setVisible(false);
                confirmPVisible.setVisible(true);
              }
              else
              {
                cImage.setImage(openEye);
                confirmPasswordField.setVisible(true);
                confirmPVisible.setVisible(false);
              }
         });
   }


}
