package info.unical.Controller;

import info.unical.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LessonController {

    @FXML
    private Button closeButton;

    @FXML
    private Label contentLabel;

    @FXML
    private Label titleLabel;

    void init()
    {

    }

    @FXML
    void closeClicked(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setTestChoiceMenu();

    }

}
