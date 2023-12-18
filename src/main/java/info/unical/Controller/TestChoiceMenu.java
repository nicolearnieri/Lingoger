package info.unical.Controller;

import info.unical.Model.ExecutorProvider;
import info.unical.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

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

    private ExecutorService executor = ExecutorProvider.getExecutor();

    User user = User.getInstance();

    Image fr = new Image(Objects.requireNonNull(getClass().getResource("/images/french-flag.png")).toExternalForm());
    Image eng = new Image(Objects.requireNonNull(getClass().getResource("/images/english-flag.png")).toExternalForm());
    Image sp = new Image(Objects.requireNonNull(getClass().getResource("/images/spanish-flag.png")).toExternalForm());
    Image pt = new Image(Objects.requireNonNull(getClass().getResource("/images/portuguese-flag.png")).toExternalForm());


    public void init() {
        choosenLanguageLabel.setText(user.getLanguage());
        if (user.getLanguage().equals("Inglese")) englishImage();
        else if (user.getLanguage().equals("Francese")) frenchImage();
        else if (user.getLanguage().equals("Spagnolo")) spanishImage();
        else if (user.getLanguage().equals("Portoghese")) portugueseImage();

    }

    void englishImage()
    {

        choosenLanguageImage.setImage(eng);
    }

    void frenchImage()
    {

        choosenLanguageImage.setImage(fr);
    }

    void spanishImage()
    {

        choosenLanguageImage.setImage(sp);
    }

    void portugueseImage()
    {

        choosenLanguageImage.setImage(pt);
    }
}

}
