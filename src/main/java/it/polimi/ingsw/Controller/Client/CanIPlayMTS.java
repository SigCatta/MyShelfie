package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.CanIPlayExecutor;

import java.io.Serializable;

public class CanIPlayMTS extends MessageToServer implements Serializable {

    private int newGameID;

    public CanIPlayMTS(int newGameID) {
        this.newGameID = newGameID;
    }

    @Override
    public void update() {
        CanIPlayExecutor.execute(this);
    }

    public int getNewGameID() {
        return newGameID;
    }
}
