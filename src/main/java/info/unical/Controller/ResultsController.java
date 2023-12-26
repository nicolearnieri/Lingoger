package info.unical.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ResultsController {

    @FXML
    private Label correctAnsLabel;

    @FXML
    private ImageView isabelleImage;

    Image isabelleOk = new Image(Objects.requireNonNull(getClass().getResource("/images/Isabelle2.png")).toExternalForm());
    Image isabelleAwesome = new Image(Objects.requireNonNull(getClass().getResource("/images/Isabelle3.png")).toExternalForm());

    public void init(int correctAnswers)
    {
        correctAnsLabel.setText("Correct answers: " + correctAnswers);
        if (correctAnswers == 3)
        {
            isabelleImage.setImage(isabelleAwesome);
        }
        else
        {
            isabelleImage.setImage(isabelleOk);
        }
    }
}
