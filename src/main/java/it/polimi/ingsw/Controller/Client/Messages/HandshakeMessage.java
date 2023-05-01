package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class HandshakeMessage extends MessageToServer implements Serializable {

    private String nickname;

    public HandshakeMessage(String nickname){
        this.nickname = nickname;
    }

    @Override
    public void update() {
        ServerController.getInstance().handshake(this);
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
