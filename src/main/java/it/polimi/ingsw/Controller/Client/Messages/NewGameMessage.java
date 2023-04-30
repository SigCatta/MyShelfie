package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class NewGameMessage extends MessageToServer implements Serializable {
    private final int NUMBER_OF_PLAYERS;

    private final String NICKNAME;

    public NewGameMessage(String nickname, int numberOfPlayers){
        this.NUMBER_OF_PLAYERS = numberOfPlayers;
        this.NICKNAME = nickname;
    }

    @Override
    public void update() {
        ServerController.getInstance().newGame(this);
    }

    public int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    @Override
    public String getNickname() {
        return NICKNAME;
    }

}
