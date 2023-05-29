package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This class is responsible for controlling the waiting room scene in the GUI.
 */
public class WaitingRoomController extends GuiController implements Initializable {
    @FXML
    Text playersNamesText;
    @FXML
    Text maxNumText;
    @FXML
    Text currentNumText;

    @FXML
    TextField gameIdText;

    private boolean entered;

    /**
     * Updates the game information in the waiting room.
     */
    @Override
    public synchronized void updateGame() {

        if (GameRepresentation.getInstance().getGameMessage() == null) {
            activateTryAgainRoutine();
            return;
        }

        //set the max number of players field
        maxNumText.setText(String.valueOf(GameRepresentation.getInstance().getMAX_PLAYER_NUMBER()));

        //set the gameid so that the player can send it to his friends
        gameIdText.setText(String.valueOf(GameRepresentation.getInstance().getGameID()));
        gameIdText.setAccessibleText(String.valueOf(GameRepresentation.getInstance().getGameID()));

        updatePlayers();
    }

    /**
     * Updates the waiting room based on the EchoMTC object received.
     * @param echoMTC the EchoMTC object containing the update information
     */
    @Override
    public void updateEcho(EchoMTC echoMTC) {
        if (echoMTC.getID() == EchoID.JOINED) {
            updatePlayers();
        }
    }

    /**
     * Updates the list of players in the waiting room.
     */
    @Override
    public synchronized void updatePlayers() {
        playersNamesText.setText("");
        List<String> players = PlayersRepresentation.getInstance().getPlayersList();
        if (players == null) {
            addPlayerToString(SocketClient.getInstance().getNickname());
            return;
        }
        addPlayerToString(players);

        //enter the game if all the players entered
        if (GameRepresentation.getInstance().getGameMessage() == null) return;
        if (players.size() == GameRepresentation.getInstance().getMAX_PLAYER_NUMBER()) {
            enterGame();
        }
    }

    /**
     * Enters the game if all the players have entered.
     */
    private synchronized void enterGame() {
        if(entered) return;
        entered = true;

        Platform.runLater(() -> StageController.changeScene("fxml/board.fxml", "Living room"));
    }

    /**
     * Adds a player's nickname to the playersNamesText field.
     * @param nickname the nickname of the player to be added
     */
    private void addPlayerToString(String nickname) {
        playersNamesText.setText(nickname + ", " + playersNamesText.getText());
    }

    /**
     * Adds multiple players' nicknames to the playersNamesText field.
     * @param nicknames the list of nicknames of players to be added
     */
    private void addPlayerToString(List<String> nicknames) {
        for (String name : nicknames) {
            playersNamesText.setText(name + ", " + playersNamesText.getText());
        }
        int numberOfPlayers = PlayersRepresentation.getInstance().getPlayersList().size();
        currentNumText.setText(String.valueOf(numberOfPlayers));
    }

    /**
     * when a player enters the waiting room, sometimes it is not able to see the game information,
     * this was solved by reloading the screen
     */
    private void activateTryAgainRoutine() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> StageController.changeScene("fxml/waiting_room.fxml", "Waiting room"));
            }
        }, 1000);
    }

    /**
     * Initializes the WaitingRoomController.
     *
     * @param url            the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGame();
        updatePlayers();
    }
}
