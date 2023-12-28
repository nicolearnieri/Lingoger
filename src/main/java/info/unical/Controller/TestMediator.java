package info.unical.Controller;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import info.unical.Model.ExecutorProvider;
import info.unical.Model.QueryCreator;
import info.unical.Model.User;
import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class TestMediator {
    private static TestMediator instance = null; //singleton
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, confirmationButton; //bottoni delle risposte, il mediatore deve avere un riferimento

    Map<Integer, Integer> map = new HashMap<>(); //mappa per le risposteù
    Map<Integer, String> answers  = new HashMap<>(); //mappa per le risposte
    Map<Button, Integer> buttonToNumber = new HashMap<>();

    private ExecutorService executor = ExecutorProvider.getExecutor();
    private User user = User.getInstance();


    private TestMediator() {}

    void init()
    {
        buttonToNumber.put(b1, 1);
        buttonToNumber.put(b2, 2);
        buttonToNumber.put(b3, 3);
        buttonToNumber.put(b4, 1);
        buttonToNumber.put(b5, 2);
        buttonToNumber.put(b6, 3);
        buttonToNumber.put(b7, 1);
        buttonToNumber.put(b8, 2);
        buttonToNumber.put(b9, 3);

    }



    public static TestMediator getInstance() {
        if (instance == null) {
            instance = new TestMediator();
        }
        return instance;
    }

    public void setButtons(Button b1, Button b2, Button b3, Button b4, Button b5, Button b6, Button b7, Button b8,
            Button b9, Button confirmationButton) {
        this.b1 = b1;
        b1.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b2 = b2;
        b2.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b3 = b3;
        b3.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b4 = b4;
        b4.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b5 = b5;
        b5.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b6 = b6;
        b6.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b7 = b7;
        b7.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b8 = b8;
        b8.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.b9 = b9;
        b9.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        this.confirmationButton = confirmationButton;
        confirmationButton.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
        init();
    }

    public void newClick(ActionEvent event) {
        Button buttonIJustClicked = (Button) event.getSource(); //mi prendo il bottone che ho cliccato dall'evento (il click)
        //int newAnswer = buttonToNumber.get(buttonIJustClicked); //mi prendo il valore associato al bottone che ho cliccato
        String answer = buttonIJustClicked.getText(); //mi prendo il testo del bottone che ho cliccato

        int question; // mi setto un valore per capire quale bottone è stato cliccato, in modo da capire a che domanda si sta rispondendo

        if (buttonIJustClicked == b1 || buttonIJustClicked == b2 || buttonIJustClicked == b3) {
            question = 1;
        } else if (buttonIJustClicked == b4 || buttonIJustClicked == b5 || buttonIJustClicked == b6) {
            question = 2;
        } else {
            question = 3;
        }

        if (answers.containsKey(question))
        {
            String oldAnswer = answers.get(question);
            if (answer.equals(oldAnswer))
            {
                answers.remove(question);
                buttonIJustClicked.setStyle("-fx-background-color: #d75c00; ; -fx-text-fill: #ffffff");
                actionOnConfirm();
                return;
            }

            //prendo il bottone associato al valore vecchio e ne cambio lo stile (lo "deseleziono")

            switch (question)
            {
                case 1: //risposta alla domanda 1
                    if (oldAnswer.equals(b1.getText()))
                    {
                        b1.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    else if (oldAnswer.equals(b2.getText()))
                    {
                        b2.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    else
                    {
                        b3.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    break;
                case 2: //risposta alla domanda 2
                    if (oldAnswer.equals(b4.getText()))
                    {
                        b4.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    else if (oldAnswer.equals(b5.getText()))
                    {
                        b5.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    else
                    {
                        b6.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    break;
                case 3:
                    if (oldAnswer.equals(b7.getText()))
                    {
                        b7.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    else if (oldAnswer.equals(b8.getText()))
                    {
                        b8.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    else
                    {
                        b9.setStyle("-fx-background-color: #d75c00; -fx-text-fill: #ffffff");
                    }
                    break;
            }
            answers.replace(question, answer); //aggiorno la risposta alla domanda
        }
        else
        {
            answers.put(question, answer); //aggiungo la risposta alla domanda
        }

        buttonIJustClicked.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d75c00; -fx-border-width: 2px; -fx-text-fill: #d75c00"); //è cambiato lol

        actionOnConfirm(); //controllo se sono state date tutte le risposte
    }

    public boolean isReady() {
        return answers.size() == 3; //se la mappa ha 3 elementi, allora sono state date tutte le risposte
    }

    public void actionOnConfirm()
    {
        if (isReady())
        {
            confirmationButton.setDisable(false); //se sono state date tutte le risposte, abilito il bottone di conferma
            return;
        }
        confirmationButton.setDisable(true); //altrimenti lo disabilito

    }

    public void checkAnswers(Vector<String> answersV, int cod) throws IOException {
        int correctAnswers = 0;
        for (int i = 0; i < answersV.size(); i++)
        {
            if (answersV.get(i).equals(answers.get(i+1)))
            { correctAnswers++; }
        }
        //aggiorno, l'utente ha svolto la lezione
        if (cod != 0)
        {
            Callable<Boolean> callable = QueryCreator.createAddTestDoneCallable(cod, user.getNomeUtente(), user.getLanguage());
            Future<Boolean> future = executor.submit(callable);
        }

        SceneHandler.getInstance().setResults(correctAnswers);
    }

    public void setButtonsValues(Vector<String> resultA) {
        b1.setText(resultA.get(0));
        b2.setText(resultA.get(1));
        b3.setText(resultA.get(2));
        b4.setText(resultA.get(3));
        b5.setText(resultA.get(4));
        b6.setText(resultA.get(5));
        b7.setText(resultA.get(6));
        b8.setText(resultA.get(7));
        b9.setText(resultA.get(8));
    }
}
