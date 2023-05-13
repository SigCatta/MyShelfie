package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class WinSceneController {
    @FXML
    Text player1Nickname;

    @FXML
    Text player2Nickname;

    @FXML
    Text player3Nickname;

    @FXML
    Text player4Nickname;

    @FXML
    Text player1Points;

    @FXML
    Text player2Points;

    @FXML
    Text player3Points;

    @FXML
    Text player4Points;

    @FXML
    ImageView player1CrownImage;

    @FXML
    ImageView player2CrownImage;

    @FXML
    ImageView player3CrownImage;

    @FXML
    ImageView player4CrownImage;

    public void setWinner() {
        //TODO
    }

    @FXML
    public void init() {
        List<Text> menuItemList = List.of(player1Nickname, player2Nickname, player3Nickname, player4Nickname);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();

        for(int i=0; i<nicknames.size(); i++) {
                menuItemList.get(i).setText(nicknames.get(i));
        }

        //TODO set points
    }
}
