package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Controller.Server.VirtualView.Messages.Message;

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
     * @param commandMap the command to be sent.
     */
    public abstract void sendCommand(HashMap<String, String> commandMap);

    public abstract void sendCommand(Message message);
}