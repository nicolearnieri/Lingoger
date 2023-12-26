package info.unical.Controller;

import com.sun.javafx.scene.SceneEventDispatcher;
import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.User;
import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
    TestController testController = TestController.getInstance();

    Image fr = new Image(Objects.requireNonNull(getClass().getResource("/images/french-flag.png")).toExternalForm());
    Image eng = new Image(Objects.requireNonNull(getClass().getResource("/images/english-flag.png")).toExternalForm());
    Image sp = new Image(Objects.requireNonNull(getClass().getResource("/images/spanish-flag.png")).toExternalForm());
    Image pt = new Image(Objects.requireNonNull(getClass().getResource("/images/portuguese-flag.png")).toExternalForm());


    public void init() throws ExecutionException, InterruptedException {
        choosenLanguageLabel.setText(user.getLanguage());
        if (user.getLanguage().equals("Inglese")) englishImage();
        else if (user.getLanguage().equals("Francese")) frenchImage();
        else if (user.getLanguage().equals("Spagnolo")) spanishImage();
        else if (user.getLanguage().equals("Portoghese")) portugueseImage();
        settingTests();
    }

    void englishImage()
    { choosenLanguageImage.setImage(eng); }

    void frenchImage()
    { choosenLanguageImage.setImage(fr); }

    void spanishImage()
    { choosenLanguageImage.setImage(sp); }

    void portugueseImage()
    { choosenLanguageImage.setImage(pt); }



    void settingTests() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = null;  //la query che recupera il numero di test in base alla lingua dell'utente

        if (user.getLanguage().equals( "Inglese"))
        {
           callable = QueryCreator.createRetrieveTestsEnglishCallable();
        }
        else if (user.getLanguage().equals( "Francese"))
        {
            callable = QueryCreator.createRetrieveTestsFrenchCallable();
        }
        else if (user.getLanguage().equals("Spagnolo"))
        {
             callable = QueryCreator.createRetrieveTestsSpanishCallable();
        }
        else if (user.getLanguage().equals("Portoghese"))
        {
           callable = QueryCreator.createRetrieveTestsPortugueseCallable();
        }

        Future<Integer> res = executor.submit(callable);
        int result = res.get();
        System.out.println(result);

        if (result != -1)
        {
            for (int i= 1; i<= result; i++ )
            {
                Button button = new Button("Test " + (i));
                button.setStyle("-fx-background-color: #d75c00");
                int finalI = i;
                button.setOnMouseClicked(event -> {
                    try {
                        generateTest(finalI,user.getLanguage());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                centerVbox.getChildren().add(button);

            }
        }

    }

    void generateTest(int i, String language) throws IOException, ExecutionException, InterruptedException {
        SceneHandler.getInstance().setTestMenu(i,language);
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
    void frenchLanguage(ActionEvent event) throws ExecutionException, InterruptedException {
        if (user.getLanguage() != "Francese")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Francese");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Francese");
            choosenLanguageLabel.setText(user.getLanguage());
            frenchImage();
        }
        settingTests();
    }

    @FXML
    void portugueseLanguage(ActionEvent event) throws ExecutionException, InterruptedException {
        if (user.getLanguage() != "Portoghese")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Portoghese");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Portoghese");
            choosenLanguageLabel.setText(user.getLanguage());
            portugueseImage();
        }
        settingTests();

    }

    @FXML
    void spanishLanguage(ActionEvent event) throws ExecutionException, InterruptedException {
        if (user.getLanguage() != "Spagnolo")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Spagnolo");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Spagnolo");
            choosenLanguageLabel.setText(user.getLanguage());
            spanishImage();
        }
        settingTests();
    }

}

