package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.CommonGoalsObserver;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
public class ObjectiveCardController {

    private final CommonGoalReader reader = new CommonGoalReader();

    static private final URL COMMON_GOAL_PACKAGE = ObjectiveCardController.class.getClassLoader().getResource("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/common_goal_cards/angoli_smussati/");
    static private final URL PERSONAL_GOAL_PACKAGE = ObjectiveCardController.class.getClassLoader().getResource("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/personal_goal_cards/angoli_smussati/");
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

    private static ObjectiveCardController instance;

    public ObjectiveCardController() {
        instance = this;
    }

    public static ObjectiveCardController getInstance() {
        return instance;
    }

    public void setPointCG1Text(int point) {
        pointCG1Text.setText(String.valueOf(point));
    }

    public void setPointCG2Text(int point) {
        pointCG2Text.setText(String.valueOf(point));
    }

    public void setCommonGoalCard1(String commonGoalCard1Path) {
        commonGoalCard1.setImage(new Image(commonGoalCard1Path));
        commonGoalCard1.setVisible(true);
    }

    public void setCommonGoalCard2(String commonGoalCard2Path) {
        commonGoalCard2.setImage(new Image(commonGoalCard2Path));
        commonGoalCard2.setVisible(true);
    }

    public void setPersonalGoalCard(String personalGoalPath) {
        personalGoalImage.setImage(new Image(personalGoalPath));
        personalGoalImage.setVisible(true);
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
        new CommonGoalsObserver().update();
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();

        setCard1Description(reader.getDescription(cardNames.get(0)));
        setCard2Description(reader.getDescription(cardNames.get(1)));

        setCommonGoalCard1(COMMON_GOAL_PACKAGE + cardNames.get(0) + ".jpg");
        setCommonGoalCard2(COMMON_GOAL_PACKAGE + cardNames.get(1) + ".jpg");

        String nickname = SocketClient.getInstance().getNickname();
        String personalGoalCardNum = PlayersRepresentation.getInstance().getPlayerByNickname(nickname).getPERSONAL_GOAL_CARD_NUMBER();

        setPersonalGoalCard(PERSONAL_GOAL_PACKAGE + personalGoalCardNum + ".jpg");
    }

    public void updateCommonGoalsPoints() {
        ArrayList<Integer> availablePoints = CommonGoalsRepresentation.getInstance().getAvailablePoints();
        setPointCG1Text(availablePoints.get(0));
        setPointCG2Text(availablePoints.get(1));
    }
}
