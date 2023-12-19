package info.unical.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public void initialize() {
        mediator.setButtons(q1a1, q1a2, q1a3, q2a1, q2a2, q2a3, q3a1, q3a2, q3a3, confirmationButton);
        //confirmationButton.setDisable(true); //il bottone di conferma è disabilitato finchè non si risponde a tutte le domande, il resto lo gestisce il mediatore
    }

    public void init(int i)
    {

    }
    @FXML //click generico sui button
    void buttonClick(ActionEvent event) {

    }

    @FXML  //cosa succede se il button di conferma, abilitato dal mediatore, viene cliccato
    void confirmationClick(ActionEvent event) {


    }

}
