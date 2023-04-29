package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.model.tiles.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.*;

public class ShelfController {
    @FXML
    GridPane matrix;

    @FXML
    ImageView personalGoalCard;

    @FXML
    Text score;

    @FXML
    Button insertDoneButton;

    public void setInsertDoneButtonVisible() {
        insertDoneButton.setVisible(true);
    }

    public void setScore(Text score) {
        this.score = score;
    }

    public void setPersonalGoalCard(ImageView personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }

    @FXML
    public void onShowOtherShelvesClicked() {
        StageController.changeScene("other_shelves.fxml", "Other shelves");
    }

    @FXML
    public void onGoToChatClicked() {
        StageController.changeScene("chat.fxml", "Chat");
    }

    @FXML
    public void onInsertDoneClicked() {
        //TODO
    }

    public void insertTile(Color color, Point position) {
        //TODO
    }
}
