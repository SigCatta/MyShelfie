package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WinSceneController extends GuiController implements Initializable {
    @FXML
    Text player1Nickname, player2Nickname, player3Nickname, player4Nickname;

    @FXML
    Text player1Points, player2Points, player3Points, player4Points;

    @FXML
    ImageView player1CrownImage, player2CrownImage, player3CrownImage, player4CrownImage;

    private void setWinner() {
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

    @Override
    public void updatePlayers(){
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Text> nicknamesTextsList = List.of(player1Nickname, player2Nickname, player3Nickname, player4Nickname);
        List<Text> pointTextsList = List.of(player1Points, player2Points, player3Points, player4Points);
        List<String> nicknames = PlayersRepresentation.getInstance().getPlayersList();

        for(int i=0; i<nicknames.size(); i++) {
            nicknamesTextsList.get(i).setText(nicknames.get(i));
            pointTextsList.get(i).setText(String.valueOf(PlayersRepresentation.getInstance().getPlayerScore(nicknames.get(i))));
        }

        setWinner();
    }
}
