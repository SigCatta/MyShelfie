package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ObjectiveCardController {
    private final CommonGoalReader reader = new CommonGoalReader();

    @FXML
    ImageView commonGoalCard1;

    @FXML
    ImageView commonGoalCard2;

    @FXML
    ImageView personalGoalImage;

    @FXML
    Text card1Description;

    @FXML
    Text card2Description;

    @FXML
    Text pointCG1Text;

    @FXML
    Text pointCG2Text;

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

    @FXML
    public void onGoBackClicked() {
        StageController.changeScene("fxml/board.fxml", "Board");
    }

    @FXML
    public void setUp() {
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();
        ArrayList<Integer> availablePoints = CommonGoalsRepresentation.getInstance().getAvailablePoints();
        //TODO add id to PersonalGoalCard and retrieve image

        setCard1Description(reader.getDescription(cardNames.get(0)));
        setCard2Description(reader.getDescription(cardNames.get(1)));
        setPointCG1Text(availablePoints.get(0));
        setPointCG2Text(availablePoints.get(1));

        setCommonGoalCard1("@..\\17_MyShelfie_BGA\\common_goal_cards\\angoli_smussati\\" + cardNames.get(0) + ".jpg");
        setCommonGoalCard2("@..\\17_MyShelfie_BGA\\common_goal_cards\\angoli_smussati\\" + cardNames.get(1) + ".jpg");
    }
}
