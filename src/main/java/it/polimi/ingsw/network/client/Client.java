package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.Server.PingPong.PongController;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Abstract class to communicate with the server. Every type of connection must implement this interface.
 */
//TODO: client is observed by the clientControllers
public abstract class Client  {
    protected static Client client_instance = null;

    protected String nickname;
    protected  int gameId;
    public static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    protected PongController pongController;

    /**
     * Sends a command to the server.
     *
     * @param commandMap the command to be sent.
     */
    public abstract void sendCommand(HashMap<String, String> commandMap);

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
        //TODO: Method called by the listener of the GUI or CLI when a new player connects and logs in
        this.nickname = nickname;
    }

    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId of the game the player is connecting to
     */
    public void setGameId(int gameId) {
        //TODO: Method called by the listener of the GUI or CLI when a new player connects and logs in
        this.gameId = gameId;
    }

    public PongController getPongController() {
        return pongController;
    }
}