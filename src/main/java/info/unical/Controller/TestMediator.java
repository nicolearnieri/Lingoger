package info.unical.Controller;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class TestMediator {
    private static TestMediator instance = null; //singleton
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, confirmationButton; //bottoni delle risposte, il mediatore deve avere un riferimento

    Map<Integer, Integer> map = new HashMap<>(); //mappa per le risposte
    Map<Button, Integer> buttonToNumber = new HashMap<>();


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
            Button b9, Button ConfirmationButton) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.b9 = b9;
        this.confirmationButton = confirmationButton;
        init();
    }

    public void newClick(ActionEvent event)
    {
        Button buttonIJustClicked = (Button) event.getSource(); //mi prendo il bottone che ho cliccato dall'evento (il click)
        int newAnswer = buttonToNumber.get(buttonIJustClicked); //mi prendo il valore associato al bottone che ho cliccato

        int question; // mi setto un valore per capire quale bottone è stato cliccato, in modo da capire a che domanda si sta rispondendo

        if(buttonIJustClicked == b1 || buttonIJustClicked == b2 || buttonIJustClicked == b3)
        { question = 1; }
        else if(buttonIJustClicked == b4 || buttonIJustClicked == b5 || buttonIJustClicked == b6)
        { question = 2; }
        else
        { question = 3;}

        if (map.containsKey(question))
        {
            //mi prendo il valore associato alla chiave question, quindi la risposta alla domanda
            int oldValue = map.get(question);
            if (newAnswer == oldValue) //se il valore associato al bottone che ho cliccato è uguale a quello che avevo già cliccato
            {
                map.remove(question); //rimuovo la risposta alla domanda
                buttonIJustClicked.setStyle("-fx-background-color: #d75c00"); //cambio lo stile del bottone in modo da "deselezionarlo"
                actionOnConfirm(); //controllo se sono state date tutte le risposte
                return;
            }

            //mi prendo il bottone associato al valore vecchio e vado a cambiare lo stile in modo da "deselezionarlo"
            switch (question)
            {
                case 1: //risposta alla domanda 1
                    if (oldValue == 1) //il button scelto prima era 1
                    { b1.setStyle("-fx-background-color: #d75c00"); }
                    else if (oldValue == 2) //il button scelto prima era 2
                    { b2.setStyle("-fx-background-color: #d75c00"); }
                    else //il button scelto prima era 3
                    { b3.setStyle("-fx-background-color: #d75c00"); }
                    break;
                case 2:
                    if (oldValue == 1)
                    { b4.setStyle("-fx-background-color: #d75c00"); }
                    else if (oldValue == 2)
                    { b5.setStyle("-fx-background-color: #d75c00");}
                    else
                    { b6.setStyle("-fx-background-color: #d75c00"); }
                    break;
                case 3:
                    if (oldValue == 1) //il button scelto prima era 1 per la risposta 3
                    { b7.setStyle("-fx-background-color: #d75c00"); }
                    else if (oldValue == 2) //il button scelto prima era 2 per la risposta 3
                    { b8.setStyle("-fx-background-color: #d75c00"); }
                    else //il button scelto prima era 3 per la risposta 3
                    { b9.setStyle("-fx-background-color: #d75c00"); }
                    break;
            }

            map.replace(question, newAnswer); //aggiorno la risposta alla domanda
        }

        else //se non c'è già una risposta alla domanda
        { map.put(question, newAnswer); //aggiungo la risposta alla domanda
        }


        //cambio lo stile al button appena selezionato
        buttonIJustClicked.setStyle("-fx-background-color: #ffffff"); //è cambiato lol
        buttonIJustClicked.setStyle("-fx-border-color: #d75c00");
        buttonIJustClicked.setStyle("-fx-text-fill: #d75c00");

        actionOnConfirm(); //controllo se sono state date tutte le risposte

    }

    public boolean isReady() {
        return map.size() == 3; //se la mappa ha 3 elementi, allora sono state date tutte le risposte
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
