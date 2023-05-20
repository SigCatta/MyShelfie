package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.model.player.Player;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

public class PlayerMTC implements MessageToClient, Serializable {
    private final String nickname;
    private final boolean IS_CONNECTED;
    private final int SCORE;
    private final HashMap<Color, Point> PERSONAL_GOAL;
    private final String PERSONAL_GOAL_CARD_NUMBER;

    public PlayerMTC(Player player) {
        nickname = player.getNickname();
        IS_CONNECTED = player.isConnected();
        SCORE = player.getScore();
        if (player.getPersonalGoal() == null) {
            PERSONAL_GOAL = null;
            PERSONAL_GOAL_CARD_NUMBER = null;
        } else {
            PERSONAL_GOAL = player.getPersonalGoal().getAchievements();
            PERSONAL_GOAL_CARD_NUMBER = player.getPersonalGoal().getCardNumber();
        }
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

    public HashMap<Color, Point> getPersonalGoal() {
        return PERSONAL_GOAL;
    }

    public String getPERSONAL_GOAL_CARD_NUMBER() {
        return PERSONAL_GOAL_CARD_NUMBER;
    }

    @Override
    public void update() {
        PlayersRepresentation.getInstance().updatePlayer(this);
    }
}
