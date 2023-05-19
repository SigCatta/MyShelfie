package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.BoardController;
import it.polimi.ingsw.View.GUI.SceneController.WaitingRoomController;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Platform;

public class PlayerObserver implements VirtualModelObserver {
    public PlayerObserver() {
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {

        if(WaitingRoomController.getInstance()!=null) {
            WaitingRoomController.getInstance().updatePlayersInfo(true);
        }
        if(BoardController.getInstance()!=null) {
           if(!PlayersRepresentation.getInstance().getPlayerInfoByNickname(SocketClient.getInstance().getNickname()).isConnected()) {
               Platform.exit();
           }
        }
    }
}
