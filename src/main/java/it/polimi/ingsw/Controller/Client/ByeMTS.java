package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.ByeExecutor;

import java.io.Serializable;

/**
 * Removes a player permanently from the game
 */
public class ByeMTS extends MessageToServer implements Serializable {

    @Override
    public void update() {
        ByeExecutor.execute(this);
    }

}
