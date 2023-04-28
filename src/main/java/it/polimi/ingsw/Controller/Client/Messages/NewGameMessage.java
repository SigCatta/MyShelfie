package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

public class NewGameMessage extends MessageToServer{
    private int numberOfPlayers;

    private String nickname;

    public NewGameMessage(String nickname, int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.nickname = nickname;
    }

    @Override
    public void update(Game game) {
        if(game != null) return; // the game must be null because it has not been created
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
