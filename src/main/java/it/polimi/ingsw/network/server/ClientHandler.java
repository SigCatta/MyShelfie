package it.polimi.ingsw.network.server;

import it.polimi.ingsw.VirtualView.Messages.MessageToClient;

/**
 * Abstract class that handle clients. Every type of connection must implement this interface.
 */
public abstract class ClientHandler {

    /**
     * Disconnects from the client.
     */
    @SuppressWarnings("unused")
    public abstract void disconnect();

    /**
     * Sends a command to the client.
     *
     * @param messageToClient the message to be sent.
     */
    @SuppressWarnings("unused")
    public abstract void sendCommand(MessageToClient messageToClient);
}