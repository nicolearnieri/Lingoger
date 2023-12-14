package info.unical.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    private TestMediator testMediator= TestMediator.getInstance();

    //init che mi disabilita in automatico il button di conferma, non cliccabile
    void init()
    {
        testMediator.resetCounter();
        confirmationButton.setDisable(true);


    }

    @FXML
    void confirmationClick(ActionEvent event) {

        testMediator.verifyTest();

    }

    @FXML
    void q1click(ActionEvent event)
    {


    }

    @FXML
    void q2click(ActionEvent event) {

    }

    @FXML
    void q3click(ActionEvent event) {

    }

}
