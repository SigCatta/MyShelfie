package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemRefillUtility;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class OtherShelvesController extends GuiController implements Initializable {
    @FXML
    GridPane shelf;
    @FXML
    Text playerName;

    private static String currentPlayerNickname = null;
    private static int currentPlayerIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentPlayerNickname == null) getFirstPlayerThatIsNotMe();
        updateShelf();
    }

    @Override
    public void updateShelf() {
        ItemTile[][] shelfModel = ShelvesRepresentation.getInstance().getShelfMessage(currentPlayerNickname).getShelf();
        System.out.println("Updating " + currentPlayerNickname + " shelf...");//TODO remove
        ItemRefillUtility.updateOtherShelfGrid(shelf, shelfModel);
    }

    /**
     * used to initialize the shelf when it is clicked for the first time
     */
    private void getFirstPlayerThatIsNotMe() {
        List<String> playersList = PlayersRepresentation.getInstance().getPlayersList();

        //get the first player that is not me
        if (playersList.get(currentPlayerIndex).equals(SocketClient.getInstance().getNickname())) {
            currentPlayerIndex++;
        }
        currentPlayerNickname = playersList.get(currentPlayerIndex);
        playerName.setText(currentPlayerNickname);
    }

    @FXML
    public void onPrevButtonClicked() {
        List<String> playersList = PlayersRepresentation.getInstance().getPlayersList();

        //decrement the index unless it is zero
        currentPlayerIndex = currentPlayerIndex <= 0 ? playersList.size() - 1 : currentPlayerIndex - 1;

        if (playersList.get(currentPlayerIndex).equals(SocketClient.getInstance().getNickname())) {
            currentPlayerIndex--;
            if (currentPlayerIndex < 0) {
                currentPlayerIndex = playersList.size() - 1;
            }
        }

        currentPlayerNickname = playersList.get(currentPlayerIndex);
        playerName.setText(currentPlayerNickname);
        updateShelf();
    }

    @FXML
    public void onNextButtonClicked() {
        List<String> playersList = PlayersRepresentation.getInstance().getPlayersList();

        //increment the index unless it is at the maximum size
        currentPlayerIndex = currentPlayerIndex >= playersList.size() - 1 ? 0 : currentPlayerIndex + 1;

        if (playersList.get(currentPlayerIndex).equals(SocketClient.getInstance().getNickname())) {
            currentPlayerIndex++;
            if (currentPlayerIndex > playersList.size() - 1) {
                currentPlayerIndex = 0;
            }
        }
        currentPlayerNickname = playersList.get(currentPlayerIndex);
        playerName.setText(currentPlayerNickname);
        updateShelf();
    }

    @FXML
    public void onBackToBoardButtonClicked() {
        StageController.changeScene("fxml/board.fxml", "My shelf");
    }


}
