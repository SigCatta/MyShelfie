package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.Executor.HandshakeExecutor;

import java.io.Serializable;

public class HandshakeMessage extends MessageToServer implements Serializable {

    private final String newNickname;

    public HandshakeMessage(String nickname) {
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
