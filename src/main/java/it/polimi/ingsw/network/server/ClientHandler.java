package it.polimi.ingsw.network.server;

import java.util.HashMap;

/**
 * Abstract class that handle clients. Every type of connection must implement this interface.
 */
public abstract class ClientHandler {
    protected String nickname;
    protected int gameId;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
       this.gameId = gameId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns the connection status.
     *
     * @return {@code true} if the client is still connected and reachable, {@code false} otherwise.
     */
    public abstract boolean isConnected();

    public abstract void setConnected(boolean connected);

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

    /**
     * The serverSocket sends a PING message to the client to check whether it's still connected
     */
    public abstract void sendPing();

    /**
     * The serverSocket sends a message to the client notifying that the nickname they chose is already being used
     */
    public void notifyNicknameAlreadyTaken() {};

    /**
     * @param nickname       the nickname of the client that has disconnected or reconnected to the game
     * @param reconnection true if the client has reconnected to the game
     * @param connectionLost true if the client hasn't responded to the PING sent by the server,
     *                       false if the client has requested to be disconnected
     */
    public abstract void sendConnectionMessage(String nickname, int gameId, boolean reconnection, boolean connectionLost);
}