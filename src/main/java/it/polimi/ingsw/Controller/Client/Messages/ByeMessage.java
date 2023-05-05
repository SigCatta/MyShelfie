package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.Executor.ByeExecutor;

import java.io.Serializable;

/**
 * this message removes a player permanently from the game
 */
public class ByeMessage extends MessageToServer implements Serializable {

    @Override
    public void update() {
        ByeExecutor.execute(this);
    }

}
