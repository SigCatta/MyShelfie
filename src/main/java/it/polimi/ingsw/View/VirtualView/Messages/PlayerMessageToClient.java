package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.model.cards.personalGoals.PersonalGoal;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class PlayerMessageToClient implements MessageToClient, Serializable {
    private final String nickname;
    private final boolean IS_CONNECTED;
    private final int SCORE;
    private final PersonalGoal PERSONAL_GOAL;

    public PlayerMessageToClient(Player player) {
        nickname = player.getNickname();
        IS_CONNECTED = player.isConnected();
        SCORE = player.getScore();
        PERSONAL_GOAL = player.getPersonalGoal();
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isConnected() {
        return IS_CONNECTED;
    }

    public int getScore() {
        return SCORE;
    }

    public PersonalGoal getPersonalGoal() {
        return PERSONAL_GOAL;
    }

    @Override
    public void update() {
        PlayersRepresentation.getInstance().updatePlayer(this);
    }
}
