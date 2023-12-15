package info.unical.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/* vengono usati due mediatori, uno che media tra le varie domande (TestMediator, si occupa del test in generale) e uno che
media tra le varie risposte (QuestionsMediator, quindi si occupa di una singola domanda alla volta);
 */
public class TestController {

    @FXML
    private Button confirmationButton;

    @FXML
    private Button q1First;

    @FXML
    private Button q1Second;

    @FXML
    private Button q1Third;

    @FXML
    private Button q2First;

    @FXML
    private Button q2Second;

    @FXML
    private Button q2Third;

    @FXML
    private Button q3First;

    @FXML
    private Button q3Second;

    @FXML
    private Button q3Third;

    @FXML
    private Label question1;

    @FXML
    private Label question2;

    @FXML
    private Label question3;

    private TestMediator testMediator= TestMediator.getInstance(); //mediatore per il test in generale

    private QuestionsMediator mediator1 = new QuestionsMediator(); //mediatore per la prima domanda
    private QuestionsMediator mediator2 = new QuestionsMediator(); //mediatore per la seconda domanda
    private QuestionsMediator mediator3 = new QuestionsMediator(); //mediatore per la terza domanda

    //init che mi disabilita in automatico il button di conferma, non cliccabile
    void init()
    {
        testMediator.resetCounter();
        confirmationButton.setDisable(true);

    }

    @FXML
    void confirmationClick(ActionEvent event)
    {
        testMediator.verifyTest(); //da implementare

    }

    @FXML
    void q1click1(ActionEvent event)
    {
        boolean answered = mediator1.controlling(1);
        testMediator.buttonClicked(answered, q1First); //va a modificare il contatore del test in generale
    }

    @FXML
    void q1click2(ActionEvent event) {
        boolean answered = mediator1.controlling(2);
        testMediator.buttonClicked(answered, q1Second); //va a modificare il contatore del test in generale

    }

    @FXML
    void q1click3(ActionEvent event)
    {
        boolean answered = mediator1.controlling(3);
        testMediator.buttonClicked(answered, q1Third); //va a modificare il contatore del test in generale
    }

    @FXML
    void q2click1(ActionEvent event)
    {
        boolean answered = mediator2.controlling(1);
        testMediator.buttonClicked(answered, q2First); //va a modificare il contatore del test in generale

    }

    @FXML
    void q2click2(ActionEvent event) {
        boolean answered = mediator2.controlling(2);
        testMediator.buttonClicked(answered, q2Second); //va a modificare il contatore del test in generale

    }

    @FXML
    void q2click3(ActionEvent event) {
        boolean answered = mediator2.controlling(3);
        testMediator.buttonClicked(answered, q2Third); //va a modificare il contatore del test in generale

    }

    @FXML
    void q3click1(ActionEvent event) {
        boolean answered = mediator3.controlling(1);
        testMediator.buttonClicked(answered, q3First); //va a modificare il contatore del test in generale

    }

    @FXML
    void q3click2(ActionEvent event) {
        boolean answered = mediator3.controlling(2);
        testMediator.buttonClicked(answered, q3Second); //va a modificare il contatore del test in generale
    }

    @FXML
    void q3click3(ActionEvent event) {
        boolean answered = mediator3.controlling(3);
        testMediator.buttonClicked(answered, q3Third); //va a modificare il contatore del test in generale
    }


}
