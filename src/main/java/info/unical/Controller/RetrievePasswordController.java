package info.unical.Controller;

import info.unical.Model.EmailSender;
import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class RetrievePasswordController {

    @FXML
    private Button confirmationButton;

    @FXML
    private TextField emailFiled;

    @FXML
    private Button noButton;

    @FXML
    private TextField usernameField;

    private ExecutorService executor = ExecutorProvider.getExecutor();

    String maiuscole = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String minuscole = "abcdefghijklmnopqrstuvwxyz";
    String numeri = "0123456789";
    String speciali = "$!€#*@?^%&";

    String caratteri = maiuscole + minuscole + numeri + speciali;

    Random random = new Random();


    public String createPw () //metodo che crea una nuova password casuale
    {
        StringBuilder password = new StringBuilder(8);

        password.append(maiuscole.charAt(random.nextInt(maiuscole.length())));
        password.append(minuscole.charAt(random.nextInt(minuscole.length())));
        password.append(numeri.charAt(random.nextInt(numeri.length())));
        password.append(speciali.charAt(random.nextInt(speciali.length())));

        for (int i = 4; i < 8; i++)
            { password.append(caratteri.charAt(random.nextInt(caratteri.length()))); }

        return password.toString();
    }


    @FXML
    void confirmationClicked(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String email = emailFiled.getText();

        Callable<Boolean> callable = QueryCreator.createFindUserCallable(username, email);
        Future<Boolean> future = executor.submit(callable);

        if (future.get())
        {
            String newPassword = createPw();
            String passwordEncoded = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            Callable<Boolean> callable1 = QueryCreator.createUpdatePasswordCallable(username, passwordEncoded);
            executor.submit(callable1);
            EmailSender.sendEmail(email, "New Password", "La tua nuova password è: " + newPassword + "\n" +
                    "Non condividerla con nessuno.");
            SceneHandler.getInstance().showInfo("La tua nuova password è stata inviata tramite email. ", "Nuova Password");
        }
            SceneHandler.getInstance().setLogIn();

    }

    @FXML
    void noButtonClicked(ActionEvent event) throws Exception {
        //click sul button "annulla"
        SceneHandler.getInstance().setLogIn();
    }

}
