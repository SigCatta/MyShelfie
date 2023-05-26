package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * This class is responsible for controlling the win scene in the GUI.
 */
public class WinSceneController extends GuiController implements Initializable {
    @FXML
    Text player1Nickname, player2Nickname, player3Nickname, player4Nickname;
    List<Text> nicknamesText;

    @FXML
    Text player1Points, player2Points, player3Points, player4Points;
    List<Text> pointsText;
    List<Integer> points;

    @FXML
    ImageView player1CrownImage, player2CrownImage, player3CrownImage, player4CrownImage;
    List<ImageView> crowns;

    /**
     * Sets the crown image for the winner player based on the points.
     */
    private void setWinner() {
        int highestIndex = 0;

        for (int i = 1; i < points.size(); i++) {
            if (points.get(i) > points.get(highestIndex)) {
                highestIndex = i;
            }
        }

        crowns.forEach(imageView -> imageView.setVisible(false));
        crowns.get(highestIndex).setVisible(true);
    }

    /**
     * Updates the players' nicknames and points in the win scene.
     */
    @Override
    public void updatePlayers() {
        ArrayList<String> players = PlayersRepresentation.getInstance().getPlayersList();
        nicknamesText.forEach(name -> name.setVisible(false));
        pointsText.forEach(point -> point.setVisible(false));

        for(int i = 0; i < players.size(); i++){
            nicknamesText.get(i).setVisible(true);
            nicknamesText.get(i).setText(players.get(i));


            int myPoint = PlayersRepresentation.getInstance().getPlayerScore(players.get(i));

            points.add(myPoint);
            pointsText.get(i).setVisible(true);
            pointsText.get(i).setText("" + myPoint);
        }

        setWinner();
    }

    /**
     * Initializes the WinSceneController.
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nicknamesText = List.of(player1Nickname, player2Nickname, player3Nickname, player4Nickname);
        pointsText = List.of(player1Points, player2Points, player3Points, player4Points);
        crowns = List.of(player1CrownImage, player2CrownImage, player3CrownImage, player4CrownImage);
        points = new ArrayList<>();

        updatePlayers();
    }
}
