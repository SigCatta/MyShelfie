package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.View.GUI.SceneController.Utility.ItemRefillUtility;
import it.polimi.ingsw.View.GUI.SceneController.Utility.ShelfMemory;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;


public class OtherShelvesController {
    @FXML
    GridPane shelf;

    @FXML
    Text playerName;

    public void setPlayerName(String nickname) {
        this.playerName.setText(nickname);
    }

    public String getPlayerName() {
        return playerName.getText();
    }

    @FXML
    public void setUp(){
        List<String> playersList = PlayersRepresentation.getInstance().getPlayersList();
        int i = 0;

        while (playersList.get(i).equals(SocketClient.getInstance().getNickname())) {
            i++;
        }
        setPlayerName(playersList.get(i));
        initShelf();
        updateShelf();
    }

    private void initShelf() {
        for (int row = 0; row < shelf.getRowCount(); row++) {
            for (int col = 0; col < shelf.getColumnCount(); col++) {
                if (ShelfMemory.get(row, col, 1) == null) {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(82);
                    imageView.setFitWidth(82);
                    ShelfMemory.put(imageView, row, col, 1);
                }
                shelf.add(ShelfMemory.get(row, col, 1), col, row);
            }
        }
    }

    @FXML
    public void onPrevButtonClicked() {
        List<String> playersList = PlayersRepresentation.getInstance().getPlayersList();
        int index = playersList.indexOf(getPlayerName());
        if(index == 0) {
            index = playersList.size()-1;
        } else {
            index--;
        }
        if(playersList.get(index).equals(SocketClient.getInstance().getNickname())) {
            index--;
            if(index < 0) {
                index = playersList.size()-1;
            }
        }
        setPlayerName(playersList.get(index));
        updateShelf();
    }

    @FXML
    public void onNextButtonClicked() {
        List<String> playersList = PlayersRepresentation.getInstance().getPlayersList();
        int index = playersList.indexOf(getPlayerName());
        if(index == playersList.size()-1) {
            index = 0;
        } else {
            index++;
        }
        if (playersList.get(index).equals(SocketClient.getInstance().getNickname())) {
            index++;
            if(index > playersList.size()-1) {
                index = 0;
            }
        }
        setPlayerName(playersList.get(index));
        updateShelf();
    }

    public void updateShelf() {
        ItemTile[][] shelfModel = ShelvesRepresentation.getInstance().getShelfMessage(getPlayerName()).getShelf();
        System.out.println("Updating " + getPlayerName() + " shelf...");//TODO remove
        ItemRefillUtility.updateOtherShelfGrid(shelfModel);
    }

    @FXML
    public void onBackToBoardButtonClicked() {
        StageController.changeScene("fxml/board.fxml", "My shelf");
    }
}
