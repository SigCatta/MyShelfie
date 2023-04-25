package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BoardController {

    @FXML
    GridPane matrix;

    @FXML
    ImageView commonGoalCard1;

    @FXML
    ImageView commonGoalCard2;

    public void setCommonGoalCard1(ImageView commonGoalCard1) {
        this.commonGoalCard1 = commonGoalCard1;
    }

    public void setCommonGoalCard2(ImageView commonGoalCard2) {
        this.commonGoalCard2 = commonGoalCard2;
    }
}


