package info.unical.Controller;

import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TestChoiceMenu {

    @FXML
    private MenuButton LanguageMenu;

    @FXML
    private ImageView choosenLanguageImage;

    @FXML
    private Label choosenLanguageLabel;

    @FXML
    private MenuItem englishItem;

    @FXML
    private MenuItem frenchItem;

    @FXML
    private MenuItem portugueseItem;

    @FXML
    private MenuItem spanishItem;

    @FXML
    private VBox centerVbox;

    private ExecutorService executor = ExecutorProvider.getExecutor();

    User user = User.getInstance();

    Image fr = new Image(Objects.requireNonNull(getClass().getResource("/images/french-flag.png")).toExternalForm());
    Image eng = new Image(Objects.requireNonNull(getClass().getResource("/images/english-flag.png")).toExternalForm());
    Image sp = new Image(Objects.requireNonNull(getClass().getResource("/images/spanish-flag.png")).toExternalForm());
    Image pt = new Image(Objects.requireNonNull(getClass().getResource("/images/portuguese-flag.png")).toExternalForm());


    public void init()
    {
        choosenLanguageLabel.setText(user.getLanguage());
        if (user.getLanguage().equals("Inglese")) englishImage();
        else if (user.getLanguage().equals("Francese")) frenchImage();
        else if (user.getLanguage().equals("Spagnolo")) spanishImage();
        else if (user.getLanguage().equals("Portoghese")) portugueseImage();
    }

    void englishImage()  { choosenLanguageImage.setImage(eng); }

    void frenchImage()  { choosenLanguageImage.setImage(fr); }

    void spanishImage() { choosenLanguageImage.setImage(sp); }

    void portugueseImage()  { choosenLanguageImage.setImage(pt); }



    void settingTests()
    {
        //eseguiamo la query porca pucela
    }

    @FXML
    void englishLanguage(ActionEvent event)
    {
        if (user.getLanguage() != "Inglese")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Inglese");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Inglese");
            choosenLanguageLabel.setText(user.getLanguage());
            englishImage();

        }
    }

    @FXML
    void frenchLanguage(ActionEvent event) {
        if (user.getLanguage() != "Francese")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Francese");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Francese");
            choosenLanguageLabel.setText(user.getLanguage());
            frenchImage();
        }
    }

    @FXML
    void portugueseLanguage(ActionEvent event) {
        if (user.getLanguage() != "Portoghese")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Portoghese");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Portoghese");
            choosenLanguageLabel.setText(user.getLanguage());
            portugueseImage();
        }

    }

    @FXML
    void spanishLanguage(ActionEvent event) {
        if (user.getLanguage() != "Spagnolo")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Spagnolo");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Spagnolo");
            choosenLanguageLabel.setText(user.getLanguage());
            spanishImage();
        }

    }

}

