package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class CommonGoalInfoController {
    @FXML
    ImageView commonGoalCard;

    @FXML
    Text cardDescription;

    @FXML
    public void onShowBoardClicked() {
        StageController.changeScene("board1.fxml", "Board");
    }

    @FXML
    public void onShowChatClicked() {
        StageController.changeScene("chat.fxml", "Chat");
    }

    public void setCommonGoalCard(String commonGoalCard1Path) {
        this.commonGoalCard.setImage(new Image(commonGoalCard1Path));
    }

    public void setCardDescription() {
        //TODO
    }
}
