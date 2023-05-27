package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.PongMTS;
import it.polimi.ingsw.network.client.SocketClient;

import java.io.Serializable;

/**
 * This class represents a message to the client indicating a ping request.
 * It triggers a response from the client in the form of a PongMTS message.
 */
public class PingMTC implements MessageToClient, Serializable {
    @Override
    public void update() {
        SocketClient.getInstance().sendCommand(new PongMTS());
    }
}
