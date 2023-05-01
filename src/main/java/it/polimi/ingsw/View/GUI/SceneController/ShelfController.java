package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.model.tiles.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

    @FXML
    ImageView itemTile1;

    @FXML
    ImageView itemTile2;

    @FXML
    ImageView itemTile3;

    public void setItemTile1Visible(String path) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(path);
        itemTile1.setVisible(true);
        itemTile1.setImage(image);
    }
    public void setItemTile2Visible(String path) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(path);
        itemTile2.setVisible(true);
        itemTile2.setImage(image);
    }
    public void setItemTile3Visible(String path) {
        javafx.scene.image.Image image = new Image(path);
        itemTile3.setVisible(true);
        itemTile3.setImage(image);
    }

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

    public void insertTile(String path, Point position) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        matrix.add(new ImageView(image), position.y, position.x);   //add(object: elem, int: column, int: row)
    }
}
