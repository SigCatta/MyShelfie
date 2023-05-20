package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.View.GUI.SceneController.Utility.CardImagesManager;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ObjectiveCardController extends GuiController implements Initializable {

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

    private void initCommonGoals() {
        CommonGoalReader reader = new CommonGoalReader();
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();

        //set the description of the common goals
        setCard1Description(reader.getDescription(cardNames.get(0)));
        setCard2Description(reader.getDescription(cardNames.get(1)));

        //get the image of the common goals
        Image card1Image = CardImagesManager.getCommonGoalImage(cardNames.get(0));
        Image card2Image = CardImagesManager.getCommonGoalImage(cardNames.get(1));

        //set the image of the common goals
        commonGoalCard1.setImage(card1Image);
        commonGoalCard2.setImage(card2Image);
    }

    private void initPersonalGoals() {
        String nickname = SocketClient.getInstance().getNickname();
        String personalGoalCardNum = PlayersRepresentation.getInstance().getPlayerInfoByNickname(nickname).getPERSONAL_GOAL_CARD_NUMBER();
        System.out.println("My personal goal is " + personalGoalCardNum); //TODO remove

        //set the image of the personal goal
        personalGoalImage.setImage(CardImagesManager.getPersonalGoalImage(personalGoalCardNum));
    }

    @Override
    public void updateCommonGoals() {
        System.out.println("Updated common goal points"); //TODO remove
        ArrayList<Integer> availablePoints = CommonGoalsRepresentation.getInstance().getAvailablePoints();
        setPointCG1Text(availablePoints.get(0));
        setPointCG2Text(availablePoints.get(1));
    }

    public void setPointCG1Text(int point) {
        pointCG1Text.setText(String.valueOf(point));
    }

    public void setPointCG2Text(int point) {
        pointCG2Text.setText(String.valueOf(point));
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


    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        initCommonGoals();
        initPersonalGoals();
    }
}
