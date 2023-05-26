package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.HandshakeExecutor;

import java.io.Serializable;

/**
 * Connects the client for the first time
 */
public class HandshakeMTS extends MessageToServer implements Serializable {

    private final String newNickname;

    public HandshakeMTS(String nickname) {
        this.newNickname = nickname;
    }

    @Override
    public void update() {
        HandshakeExecutor.execute(this);
    }

    public String getNewNickname() {
        return newNickname;
    }
}
