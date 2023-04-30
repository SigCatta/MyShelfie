package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class NewGameMessage extends MessageToServer implements Serializable {
    private int numberOfPlayers;

    private String nickname;

    public NewGameMessage(String nickname, int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.nickname = nickname;
    }

    @Override
    public void update() {
        ServerController.getInstance().newGame(this);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
