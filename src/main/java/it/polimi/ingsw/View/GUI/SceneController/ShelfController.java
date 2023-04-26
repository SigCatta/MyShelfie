package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ShelfController {
    @FXML
    GridPane matrix;

    @FXML
    ImageView personalGoalCard;

    @FXML
    Text score;

    public void setScore(Text score) {
        this.score = score;
    }

    public void setPersonalGoalCard(ImageView personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }
}
