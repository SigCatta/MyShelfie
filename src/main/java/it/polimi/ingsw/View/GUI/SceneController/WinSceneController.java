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
        List<Integer> pointList = List.of(Integer.parseInt(player1Points.getText()), Integer.parseInt(player1Points.getText()),
                Integer.parseInt(player1Points.getText()), Integer.parseInt(player1Points.getText()));

        int maxScore = pointList.stream().max(Integer::compare).orElse(0);
        if(pointList.indexOf(maxScore)==0) {
            player1CrownImage.setVisible(true);
        } else if(pointList.indexOf(maxScore)==1) {
            player2CrownImage.setVisible(true);
        } else if(pointList.indexOf(maxScore)==2) {
            player3CrownImage.setVisible(true);
        } else {
            player4CrownImage.setVisible(true);
        }
    }

    @FXML
    public void init() {
        List<Text> nicknamesTextsList = List.of(player1Nickname, player2Nickname, player3Nickname, player4Nickname);
        List<Text> pointTextsList = List.of(player1Points, player2Points, player3Points, player4Points);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();

        for(int i=0; i<nicknames.size(); i++) {
            nicknamesTextsList.get(i).setText(nicknames.get(i));
            pointTextsList.get(i).setText(String.valueOf(PlayersRepresentation.getInstance().getPlayerScore(nicknames.get(i))));
        }
    }
}
