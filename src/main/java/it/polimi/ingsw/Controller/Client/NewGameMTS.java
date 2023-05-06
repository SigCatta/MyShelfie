package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.NewGameExecutor;

import java.io.Serializable;

public class NewGameMTS extends MessageToServer implements Serializable {
    private final int NUMBER_OF_PLAYERS;

    public NewGameMTS(int numberOfPlayers){
        this.NUMBER_OF_PLAYERS = numberOfPlayers;
    }

    @Override
    public void update() {
        NewGameExecutor.execute(this);
    }

    public int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

}
