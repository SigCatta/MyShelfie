package it.polimi.ingsw.network.server;

import java.util.HashMap;

/**
 * Interface to handle clients. Every type of connection must implement this interface.
 */
public interface ClientHandler {

    /**
     * Returns the connection status.
     *
     * @return {@code true} if the client is still connected and reachable, {@code false} otherwise.
     */
    boolean isConnected();

    void setConnected(boolean connected);

    /**
     * Disconnects from the client.
     */
    void disconnect();

    /**
     * Sends a command to the client.
     *
     * @param commandMap the command to be sent.
     */
    void sendCommand(HashMap<String, String> commandMap);

    /**
     * The serverSocket sends a PING message to the client to check whether it's still connected
     */
    void sendPing();

    /**
     * @param nickname       the nickname of the client that has disconnected or reconnected to the game
     * @param reconnection true if the client has reconnected to the game
     * @param connectionLost true if the client hasn't responded to the PING sent by the server,
     *                       false if the client has requested to be disconnect
     */
    void sendConnectionMessage(String nickname, boolean reconnection, boolean connectionLost);
}