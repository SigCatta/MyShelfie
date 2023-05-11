package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ObjectiveCardController {
    @FXML
    ImageView commonGoalCard1;

    @FXML
    ImageView commonGoalCard2;

    @FXML
    Text card1Description;

    @FXML
    Text card2Description;

    @FXML
    Text pointCG1Text;

    @FXML
    Text pointCG2Text;

    @FXML
    Text myPointsText;

    public void setPointCG1Text(int point) {
        pointCG1Text.setText(String.valueOf(point));
    }

    public void setPointCG2Text(int point) {
        pointCG2Text.setText(String.valueOf(point));
    }

    public void setCommonGoalCard1(String commonGoalCard1Path) {
        this.commonGoalCard1.setImage(new Image(commonGoalCard1Path));
    }

    public void setCommonGoalCard2(String commonGoalCard2Path) {
        this.commonGoalCard2.setImage(new Image(commonGoalCard2Path));
    }

    public void setCard1Description(String description) {
        this.card1Description.setText(description);
    }

    public void setCard2Description(String description) {
        this.card2Description.setText(description);
    }

    public void setMyPointsText(int points) {
        this.myPointsText.setText(String.valueOf(points));
    }
}
