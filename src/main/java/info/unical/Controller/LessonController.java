package info.unical.Controller;

import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.User;
import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class LessonController {

    @FXML
    private Button closeButton;

    @FXML
    private Label contentLabel;

    @FXML
    private Label titleLabel;

    private ExecutorService executor = ExecutorProvider.getExecutor();

    User user = User.getInstance();

    int currentLesson = 0;

    public void init(int finalI, String language) throws ExecutionException, InterruptedException {
        Callable<Vector<String>> callable = null;
        Vector<String> infos = null;
        currentLesson = finalI;


        //recupera la lezione finalI in base alla lingua (i codici sono univoci per tabella, non in tutto il db)
        if (language.equals("Inglese"))
        { callable = QueryCreator.createRetrieveEnglishLessonInfoCallable(finalI); }
        else if ( language.equals("Francese"))
        { callable = QueryCreator.createRetrieveFrenchLessonInfoCallable(finalI); }
        else if ( language.equals("Portoghese"))
        { callable = QueryCreator.createRetrievePortugueseLessonInfoCallable(finalI); }
        else if ( language.equals("Spagnolo"))
        { callable = QueryCreator.createRetrieveSpanishLessonInfoCallable(finalI); }

        Future<Vector<String>> future = executor.submit(callable);
        infos= future.get();

        if(infos.size() == 3)
        {
            titleLabel.setText(infos.get(0));
            contentLabel.setText(infos.get(2));
        }


    }
    @FXML
    void closeClicked(ActionEvent event) throws Exception
    {
        //aggiorno, l'utente ha svolto la lezione
        Callable<Boolean> callable = QueryCreator.createAddLessonDoneCallable(currentLesson, user.getNomeUtente(), user.getLanguage());
        Future<Boolean> future = executor.submit(callable);

        SceneHandler.getInstance().setTestChoiceMenu();
    }


}
