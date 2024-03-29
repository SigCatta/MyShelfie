package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.Client.MessageToServer;

import java.util.logging.Logger;

/**
 * Abstract class to communicate with the server. Every type of connection must implement this interface.
 */
public abstract class Client {

    protected String nickname;
    public static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    /**
     * Sends a command to the server.
     *
     * @param commandMap the command to be sent.
     */
    public abstract void sendCommand(MessageToServer commandMap);

    /**
     * Asynchronously reads a command from the server and notifies the ClientController.
     */
    public abstract void readCommand();

    /**
     * Disconnects from the server.
     */
    public abstract void disconnect();

    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname chosen by the player
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}