package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.Messages.PongMessage;
import it.polimi.ingsw.network.client.SocketClient;

import java.io.Serializable;

public class PingMessageToClient implements MessageToClient, Serializable {
    @Override
    public void update() {
        SocketClient.getInstance().sendCommand(new PongMessage());
    }
}
