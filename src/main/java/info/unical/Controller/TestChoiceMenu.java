package info.unical.Controller;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;
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
    private VBox testVbox;

    @FXML
    private VBox lessonVbox;

    private ExecutorService executor = ExecutorProvider.getExecutor();

    User user = User.getInstance();
    TestController testController = TestController.getInstance();

    Vector<Callable<Integer>> tasks = new Vector<Callable<Integer>>();
    Vector<String> lessonInfos = new Vector<String>();

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
        generateCallablesForCounts();
    }

    void englishImage()
    { choosenLanguageImage.setImage(eng); }

    void frenchImage()
    { choosenLanguageImage.setImage(fr); }

    void spanishImage()
    { choosenLanguageImage.setImage(sp); }

    void portugueseImage()
    { choosenLanguageImage.setImage(pt); }



    void generateCallablesForCounts() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = null;  //la query che recupera il numero di test in base alla lingua dell'utente
        Callable<Integer> callable2 = null;  //la query che recupera il numero di test in base alla lingua dell'utente
        Callable<Vector<String>> infosCallable = null;

        if (user.getLanguage().equals( "Inglese"))
        {
            callable = QueryCreator.createRetrieveTestsEnglishCallable();
            callable2 = QueryCreator.createRetrieveEnglishLessonsCallable();
            infosCallable= QueryCreator.createRetrieveEnglishDescriptionCallable();

        }
        else if (user.getLanguage().equals( "Francese"))
        {
            callable = QueryCreator.createRetrieveTestsFrenchCallable();
            callable2 = QueryCreator.createRetrieveFrenchLessonsCallable();
            infosCallable= QueryCreator.createRetrieveFrenchDescriptionCallable();
        }
        else if (user.getLanguage().equals("Spagnolo"))
        {
            callable = QueryCreator.createRetrieveTestsSpanishCallable();
            callable2= QueryCreator.createRetrieveSpanishLessonsCallable();
            infosCallable= QueryCreator.createRetrieveSpanishDescriptionCallable();
        }
        else if (user.getLanguage().equals("Portoghese"))
        {
            callable = QueryCreator.createRetrieveTestsPortugueseCallable();
            callable2 = QueryCreator.createRetrievePortugueseLessonsCallable();
            infosCallable= QueryCreator.createRetrievePortugueseDescriptionCallable();

        }

        tasks.add(callable);
        tasks.add(callable2);
        settingTests();

        Future<Vector<String>> future = executor.submit(infosCallable);
        lessonInfos = future.get();


        settingLessons();

    }

    void settingLessons() throws ExecutionException, InterruptedException {
        lessonVbox.getChildren().clear();
        lessonVbox.getChildren().add(new Label("Lezioni"));

        Future<Integer> res = executor.submit(tasks.get(1));
        int result = res.get();
        System.out.println(result);

        if (result != -1)
        {
            for (int i= 1; i<= result; i++ )
            {
                HBox hBox = new HBox();
                Label label = new Label(lessonInfos.get(i-1));
                label.setPrefSize(330, 35 );
                hBox.getChildren().add(label);

                Button button = new Button("Lezione " + (i));
                button.setPrefSize(100, 35 );
                button.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff ");

                int finalI = i;
                button.setOnMouseClicked(event -> {
                    try {
                        generateLesson(finalI,user.getLanguage());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                hBox.getChildren().add(button);
                lessonVbox.getChildren().add(hBox);
            }
        }

    }



    void settingTests() throws ExecutionException, InterruptedException
    {
        testVbox.getChildren().clear();
        testVbox.getChildren().add(new Label("Test"));

        Future<Integer> res = executor.submit(tasks.get(0));
        int result = res.get();
        System.out.println(result);

        if (result != -1)
        {
            for (int i= 1; i<= result; i++ )
            {
                Button button = new Button("Test " + (i));
                button.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff ");
                button.setPrefSize(100, 35);

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
                testVbox.getChildren().add(button);
            }
        }

    }

    void generateTest(int i, String language) throws IOException, ExecutionException, InterruptedException {
        SceneHandler.getInstance().setTestMenu(i,language);
    }

    private void generateLesson(int finalI, String language) throws IOException, ExecutionException, InterruptedException {
        SceneHandler.getInstance().setLesson(finalI,language);
    }

    @FXML
    void englishLanguage(ActionEvent event) throws ExecutionException, InterruptedException {
        if (user.getLanguage() != "Inglese")
        {
            Callable<Boolean> updateTask = QueryCreator.createUpdateOnUser(user.getNomeUtente(), "Inglese");
            Future<Boolean> future = executor.submit(updateTask);
            user.setLanguage("Inglese");
            choosenLanguageLabel.setText(user.getLanguage());
            englishImage();
        }
        generateCallablesForCounts();
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
        generateCallablesForCounts();
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
        generateCallablesForCounts();

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
        generateCallablesForCounts();
    }

}

