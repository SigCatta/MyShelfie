package it.polimi.ingsw.network.server;

import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;

import java.util.HashMap;

/**
 * Abstract class that handle clients. Every type of connection must implement this interface.
 */
public abstract class ClientHandler {

    /**
     * Disconnects from the client.
     */
    public abstract void disconnect();

    /**
     * Sends a command to the client.
     *
     * @param messageToClient the message to be sent.
     */
    public abstract void sendCommand(MessageToClient messageToClient);
}