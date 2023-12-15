package info.unical.Controller;

import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class TestMediator {
    private static TestMediator instance = null;
    private int buttonCounter = 0;

    public static TestMediator getInstance() {
        if (instance == null) {
            instance = new TestMediator();
        }
        return instance;
    }

    public void incrementCounter()
    {
        buttonCounter++;
    }

    public void decrementCounter()
    {
        buttonCounter--;
    }
    public void resetCounter()
    {
        buttonCounter = 0;
    }

    public int getCounter()
    {
        return buttonCounter;
    }

    public boolean isTestFinished()
    {
        return buttonCounter == 3;
    }

    public void buttonClicked(boolean alreadyClicked, Button confirmationButton)
    {
        if(alreadyClicked)
        {
            decrementCounter(); //se il button era selezionato e lo clicco nuovamente, decremento il counter (l'ho deselezionato)
        }
        else
        {
            incrementCounter(); //al contrario, seleziono il button non cliccato prima e incremento il counter
        }


        if (isTestFinished())
        { confirmationButton.setDisable(false); }
        else
        { confirmationButton.setDisable(true); }
    }

    public void verifyTest(){}; //da implementare

}
