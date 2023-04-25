package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ShelfController {
    @FXML
    GridPane matrix;

    @FXML
    ImageView personalGoalCard;

    public void setPersonalGoalCard(ImageView personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }
}
