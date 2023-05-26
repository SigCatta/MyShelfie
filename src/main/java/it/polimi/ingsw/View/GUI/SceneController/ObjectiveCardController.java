package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.View.GUI.SceneController.Utility.CardImagesManager;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class is responsible for controlling the objective card scene in the GUI.
 */
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

    /**
     * Initializes the common goals.
     * Retrieves the common goal card names from CommonGoalsRepresentation and sets the descriptions and images accordingly.
     */
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

    /**
     * Initializes the personal goal.
     * Retrieves the personal goal card number of the current player from PlayersRepresentation and sets the image accordingly.
     */
    private void initPersonalGoals() {
        String nickname = SocketClient.getInstance().getNickname();
        String personalGoalCardNum = PlayersRepresentation.getInstance().getPlayerInfoByNickname(nickname).getPERSONAL_GOAL_CARD_NUMBER();

        //set the image of the personal goal
        personalGoalImage.setImage(CardImagesManager.getPersonalGoalImage(personalGoalCardNum));
    }

    /**
     * Updates the scene based on the game state.
     * If the game state is END, navigates to the win scene.
     */
    @Override
    public void updateGame() {
        if (GameRepresentation.getInstance().getGameState() == GameState.END) {
            Platform.runLater(() -> StageController.changeScene("fxml/win_scene.fxml", "Game Finished"));
        }
    }

    /**
     * Updates the common goals.
     * Retrieves the available points from CommonGoalsRepresentation and updates the point texts.
     */
    @Override
    public void updateCommonGoals() {
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

    /**
     * Handles the event when the "Go Back" button is clicked.
     * Navigates back to the board scene.
     */
    @FXML
    public void onGoBackClicked() {
        StageController.changeScene("fxml/board.fxml", "Board");
    }

    /**
     * Initializes the objective card scene.
     * Calls the methods to initialize the common goals, personal goals, and update the common goals.
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCommonGoals();
        initPersonalGoals();
        updateCommonGoals();
    }
}
