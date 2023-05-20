package it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers;

import it.polimi.ingsw.View.GUI.SceneController.StageController;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.network.client.SocketClient;

public class MyScoreObserver implements VirtualModelObserver {

    private int prevScore;

    public MyScoreObserver() {
        prevScore = 0;
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        int newScore = PlayersRepresentation.getInstance().getPlayerScore(SocketClient.getInstance().getNickname());

        if (prevScore == newScore) return;

        prevScore = newScore;
        StageController.getController().updateMyScore();
    }
}
