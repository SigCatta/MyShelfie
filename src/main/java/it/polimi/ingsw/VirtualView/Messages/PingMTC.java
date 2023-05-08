package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.PongMTS;
import it.polimi.ingsw.network.client.SocketClient;

import java.io.Serializable;

public class PingMTC implements MessageToClient, Serializable {
    @Override
    public void update() {
        SocketClient.getInstance().sendCommand(new PongMTS());
    }
}
