package info.unical.Controller;

import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TestController {

    @FXML
    private Button confirmationButton;

    @FXML
    private Button q1a1;

    @FXML
    private Button q1a2;

    @FXML
    private Button q1a3;

    @FXML
    private Button q2a1;

    @FXML
    private Button q2a2;

    @FXML
    private Button q2a3;

    @FXML
    private Button q3a1;

    @FXML
    private Button q3a2;

    @FXML
    private Button q3a3;

    @FXML
    private Label question1;

    @FXML
    private Label question2;

    @FXML
    private Label question3;

    private TestMediator mediator = TestMediator.getInstance();
    private ExecutorService executor = ExecutorProvider.getExecutor();
    private User user = User.getInstance();
    private static TestController instance = null;
    Vector<String> answers = new Vector<>();

    int currentTest=0;

    public static TestController getInstance() {
    	if(instance == null)
    		instance = new TestController();
    	return instance;
    }

    public void initialize(int i, String language) throws ExecutionException, InterruptedException {
        mediator.setButtons(q1a1, q1a2, q1a3, q2a1, q2a2, q2a3, q3a1, q3a2, q3a3, confirmationButton);
        setTest(i, language);
    }

    public void setTest(int i,String language) throws ExecutionException, InterruptedException {
        //in base a language chiami la query giusta con i come parametro
        //in base a i setta il test corretto

        currentTest = i;

        Callable<Vector<Object>> callableForQ = null;  //la query che recupera le domande per il test
        Callable<Vector<String>> callableForA = null;  //la query che recupera le risposte per ogni domanda del test
        if (user.getLanguage().equals( "Inglese"))
        {
            callableForQ = QueryCreator.createRetrieveQuestionsEnglishCallable(i);
            callableForA = QueryCreator.createRetrieveAnswersEnglishCallable(i);
        }
        else if (user.getLanguage().equals("Francese"))
        {
            callableForQ = QueryCreator.createRetrieveQuestionsFrenchCallable(i);
            callableForA = QueryCreator.createRetrieveAnswersFrenchCallable(i);
        }
        else if (user.getLanguage().equals("Spagnolo"))
        {
            callableForQ = QueryCreator.createRetrieveQuestionsSpanishCallable(i);
            callableForA = QueryCreator.createRetrieveAnswersSpanishCallable(i);
        }
        else if (user.getLanguage().equals("Portoghese"))
        {
            callableForQ = QueryCreator.createRetrieveQuestionsPortugueseCallable(i);
            callableForA = QueryCreator.createRetrieveAnswersPortugueseCallable(i);
        }



        Future<Vector<Object>> resQ = executor.submit(callableForQ);
        Vector<Object> resultQ = resQ.get();

        question1.setText((String) resultQ.get(0));
        answers.add((String) resultQ.get(2));

        question2.setText((String) resultQ.get(3));
        answers.add((String) resultQ.get(5));

        question3.setText((String) resultQ.get(6));
        answers.add((String) resultQ.get(8));

        Future<Vector<String>> resA = executor.submit(callableForA);
        Vector<String> resultA = resA.get();

        mediator.setButtonsValues(resultA);

    }
    @FXML //click generico sui button
    void buttonClick(ActionEvent event)
    {
        mediator.newClick(event);
    }

    @FXML  //cosa succede se il button di conferma, abilitato dal mediatore, viene cliccato
    void confirmationClick(ActionEvent event) throws IOException {
        mediator.checkAnswers(answers, currentTest);

    }

}
