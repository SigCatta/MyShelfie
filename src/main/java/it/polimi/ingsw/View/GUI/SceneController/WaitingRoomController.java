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

public class WaitingRoomController extends GuiController implements Initializable {
    @FXML
    Text playersNamesText;
    @FXML
    Text maxNumText;
    int maxNumberOfPlayers = -1;

    @FXML
    Text currentNumText;

    @FXML
    TextField gameIdText;

    @Override
    public void updateGame() {

        if (GameRepresentation.getInstance().getGameMessage() == null) return;

        //set the max number of players field
        maxNumText.setText(String.valueOf(GameRepresentation.getInstance().getMAX_PLAYER_NUMBER()));

        //set the gameid so that the player can send it to his friends
        gameIdText.setText(String.valueOf(GameRepresentation.getInstance().getGameID()));
        gameIdText.setAccessibleText(String.valueOf(GameRepresentation.getInstance().getGameID()));

        updatePlayers();
    }

    @Override
    public void updateEcho(EchoMTC echoMTC) {
        if (echoMTC.getID() == EchoID.JOINED) {
            updatePlayers();
        }
    }

    @Override
    public void updatePlayers() {
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

    private void enterGame() {
        Platform.runLater(() -> StageController.changeScene("fxml/board.fxml", "Living room"));
    }

    private void addPlayerToString(String nickname) {
        playersNamesText.setText(nickname + ", " + playersNamesText.getText());
    }

    private void addPlayerToString(List<String> nicknames) {
        for (String name : nicknames) {
            playersNamesText.setText(name + ", " + playersNamesText.getText());
        }
        int numberOfPlayers = PlayersRepresentation.getInstance().getPlayersList().size();
        currentNumText.setText(String.valueOf(numberOfPlayers));
        System.out.println(maxNumberOfPlayers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGame();
        updatePlayers();
    }
}
