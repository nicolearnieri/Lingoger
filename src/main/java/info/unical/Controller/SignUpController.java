package info.unical.Controller;

import info.unical.Model.EmailSender;
import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.ValidatorUtility;
import info.unical.View.SceneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


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
    private TextField emailField;

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
    private TextField usernameField;

    @FXML
    private Button accessButton;

    static ObservableList<String> languageList = FXCollections.observableArrayList("Inglese","Francese", "Spagnolo", "Portoghese");

    Image openEye = new Image(Objects.requireNonNull(getClass().getResource("/images/open_eye.png")).toExternalForm());
    Image eyeOff = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_off.png")).toExternalForm());
    private ExecutorService executor = ExecutorProvider.getExecutor();


    public void init()
   {
       languageChooser.setItems(languageList);
       passwordFields();

   }

   public void passwordFields() {
       passwordField.textProperty().bindBidirectional(seePField.textProperty());
       confirmPasswordField.textProperty().bindBidirectional(confirmPVisible.textProperty());
       image.setImage(openEye); //password
       cImage.setImage(openEye); //confirmation password

       image.setOnMouseClicked(event -> {
           if (image.getImage() == openEye) {
               image.setImage(eyeOff);
               passwordField.setVisible(false);
               seePField.setVisible(true);
           } else {
               image.setImage(openEye);
               seePField.setVisible(false);
               passwordField.setVisible(true);
           }
       });

       cImage.setOnMouseClicked(event -> {
           if (cImage.getImage() == openEye) {
               cImage.setImage(eyeOff);
               confirmPasswordField.setVisible(false);
               confirmPVisible.setVisible(true);
           } else {
               cImage.setImage(openEye);
               confirmPasswordField.setVisible(true);
               confirmPVisible.setVisible(false);
           }
       });

   }
       @FXML
       private String GetFromTextField(TextField t)
       {
           return t.getText();
       }

    @FXML
    private String GetPassword(PasswordField f) //metodo generico per prendere le password
    { return f.getText(); }

    public void openLogIn() throws Exception { SceneHandler.getInstance().setLogIn(); }

    public void showUsernameError() {
        // Mostra un messaggio di errore all'utente per l'username che non va bene
        SceneHandler.getInstance().showError("Il nome utente da te scelto corrisponde ad un altro account.\nScegline un altro oppure fai il log in.", "Errore nella registrazione");
    }

    public void showEmailError(String content) {
        // Mostra un messaggio di errore all'utente, passato come parametro
        SceneHandler.getInstance().showError("L'email da te inserita non è nel formato giusto,\noppure è associata ad un altro account. Riprova.","Errore nella registrazione"); }
    public void showPasswordError (){
        // Mostra un messaggio di errore all'utente riguardante la Password errata
        SceneHandler.getInstance().showError("Le password da te inserite non corrispondono, ti chiediamo di ricontrollarle.","Errore nella registrazione"); }

    public void genericError ()
    { SceneHandler.getInstance().showError("Si è verificato un errore. Ti invitiamo a riprovare più tardi.","Errore"); }
    private void regSuccess()  //registrazione a buon fine
    { SceneHandler.getInstance().showInfo("La registrazione è andata a buon fine.\nA breve riceverai un'email con i dettagli.","Registrazione completata"); }

    @FXML
    private void registration(Event event)
    {
        String email= GetFromTextField(emailField);
        String username = GetFromTextField(usernameField);
        String password = GetPassword(passwordField);
        String repPassword = GetPassword(confirmPasswordField);
        String language = languageChooser.getValue();
        boolean eqPasswords = false;
        boolean passwordOk = false;
        boolean emailOk = false;
        boolean usernameOk = false;
        boolean languageOk = false;

        try {
            Callable<Boolean> userCallable = QueryCreator.createUsernameNotExists(username);
            Future<Boolean> result = executor.submit(userCallable);
            Boolean res = result.get();

            //controllo username valido: non dev'essere ripetuto nella query
            if (res) { usernameOk = true; }
            else { showUsernameError(); }


            if ( ValidatorUtility.isValidEmail(email)) //se il formato è valido
            {
                Callable<Boolean> emailCallable = QueryCreator.createEmailNotExists(email); //verifico che l'email non sia già presente nel db.
                result = executor.submit(emailCallable);
                res = result.get();

                if (res) {emailOk = true;}
                else { showEmailError("L'email inserita risulta già registrata.\\nSi prega di sceglierne un'altra o di effettuare l'accesso."); }
            }
            else { showEmailError("L'email inserita non è valida.\\nSi prega di sceglierne una nel formato:\\nmariorossi@gmail.com\n"); }

            if (ValidatorUtility.isValidPassword(password)) passwordOk= true;

            if (password.equals(repPassword) ) {eqPasswords = true;}
            else {showPasswordError();}

            if (language!=null) languageOk = true;
            else {SceneHandler.getInstance().showError("Per proseguire con la registrazione, seleziona una lingua.", "Errore nella registrazione");}
            if (eqPasswords && usernameOk && emailOk && passwordOk && languageOk)
            {

                String passwordEncoded= BCrypt.hashpw(password, BCrypt.gensalt(12)); //CODIFICA PASSWORD
                Callable<Boolean> insTask = QueryCreator.createInsertUser(username, email, passwordEncoded, language);
                Future<Boolean> insRes = executor.submit(insTask);//oggetto prodotto da un'operazione asincrona
                res = insRes.get();
                if (res)
                { //messaggio avviso che la registrazione è andata a buon fine e il profilo può essere completato da impostazioni
                    SceneHandler.getInstance().setLogIn();
                    regSuccess();
                    EmailSender.sendEmail(email, "Conferma registrazione", "La registrazione è avvenuta con successo.\nIl team di Lingoger ti ringrazia e ti augura buona fortuna con il tuo percorso di apprendimento!");

                }
            }
            else {genericError();}
        }
        catch (Exception exc) {
            exc.printStackTrace(); }
    }

    @FXML
    void openLogIn(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setLogIn();
    }


}


