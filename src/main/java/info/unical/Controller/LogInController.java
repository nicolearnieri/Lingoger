package info.unical.Controller;

import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.User;
import info.unical.Model.ValidatorUtility;
import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class LogInController {

    @FXML
    private ImageView image;

    @FXML
    private Button logButton;

    @FXML
    private TextField passwordField;

    @FXML
    private PasswordField passwordHiddenField;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField usernameOrEmailField;

    Image openEye = new Image(Objects.requireNonNull(getClass().getResource("/images/open_eye.png")).toExternalForm());
    Image eyeOff = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_off.png")).toExternalForm());

    private ExecutorService executor = ExecutorProvider.getExecutor();

    public void init() { passwordFields(); }


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


    @FXML
    void openSignUp(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setSignUp();
    }


    private void logInError (String message){
        // Mostra un messaggio di errore all'utente
        SceneHandler.getInstance().showError(message, "Errore");
    }

    private void logSuccess() {
        SceneHandler.getInstance().showInfo("L'accesso al tuo account è stato\neffettuato con successo.", "Accesso effettuato");
    }

    @FXML
    public void logInUser () throws Exception {
        String user = usernameOrEmailField.getText();
        String password = passwordField.getText();
        boolean email = ValidatorUtility.isValidEmail(user);
        boolean userExists = false;


        if (email)
        {  //Se l'utente cerca di accedere tramite email:
            Callable<Boolean> emailCallable = QueryCreator.createEmailNotExists(user);
            Future<Boolean> result = executor.submit(emailCallable);
            Boolean res = result.get();
            if (res)
                logInError("L'email inserita non è associata a nessun account esistente."); //l'email non esiste
            else userExists = true; //l'utente esiste
        }
        else
        { //cerca di accedere tramite username
            Callable<Boolean> userCallable = QueryCreator.createUsernameNotExists(user);
            Future<Boolean> result = executor.submit(userCallable);
            Boolean res = result.get();
            if (res)
                logInError("Il nome utente inserito non è associato a nessun acoount esistente."); //il nome utente non esiste
            else userExists = true;
        }

        if (userExists)
        {
            Callable<String> getPwCallable = QueryCreator.createGetPassword(user);
            Future<String> resultP = executor.submit(getPwCallable);
            String resP = resultP.get();
            boolean check = BCrypt.checkpw(password, resP); //controllo che la password inserita sia corretta

            if (check) {
                //si settano le info dell'utente corrente (che ha fatto l'accesso)
                User cU = User.getInstance();

                if (email)
                {
                    cU.setEmail(user);
                    Callable<String> getUserCallable = QueryCreator.createGetUsername(user);
                    Future<String> result = executor.submit(getUserCallable);
                    String resS = result.get();
                    cU.setNomeUtente(resS);
                }
                else
                {
                    cU.setNomeUtente(user);
                    Callable<String> getEmailCallable = QueryCreator.createGetEmail(user);
                    Future<String> result = executor.submit(getEmailCallable);
                    String resE= result.get();
                    cU.setEmail(resE);
                }

                logSuccess();
                SceneHandler.getInstance().closeStage(SceneHandler.getInstance().returnLogInSignUpStage());


                Callable<Vector<String>> info = QueryCreator.returnUserInfoCallable(user);
                Future<Vector<String>> exec = executor.submit(info);
                Vector<String> res = exec.get();
                if (res.size() ==1) {
                    cU.setLanguage(res.get(0));
                }
                SceneHandler.getInstance().setTestChoiceMenu();
            }
            else logInError("La password da te inserita potrebbe\nessere errata.");
        }
    }


    @FXML
    void sendByKey(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER)
        {
            logInUser();
        }
    }


}
