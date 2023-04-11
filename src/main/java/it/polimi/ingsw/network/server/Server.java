package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Controller.PingController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Server class that generates a socket server.
 */
public class Server {

    private final Map<String, ClientHandler> clientHandlerMap;

    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private final Object lock;

    PingController pingController;

    public Server(int pingTimeout) {
        this.clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        this.lock = new Object();
        this.pingController = new PingController(this, pingTimeout);
    }

    /**
     * Adds a client to be managed by the server instance.
     *
     * @param nickname      the nickname associated with the client.
     * @param clientHandler the ClientHandler associated with the client.
     */
    public void addClient(String nickname, ClientHandler clientHandler) {
        if (!clientHandlerMap.containsKey(nickname)) {
                clientHandlerMap.put(nickname, clientHandler);
                pingController.addToClientMap(nickname);
                Server.LOGGER.info(nickname + " added to the game");
                //TODO: sends commandMap to CommandParser
        } else {
            clientHandler.disconnect();
        }
    }

    /**
     * Removes a client given his nickname.
     *
     * @param nickname  the nickname of the client to be removed
     */
    public void removeClient(String nickname) {
        clientHandlerMap.remove(nickname);
        pingController.removeFromClientMap(nickname);
        LOGGER.info(() -> "Removed " + nickname + " from the client list.");
    }

    /**
     * Forwards a received commandMap from the client to the GameController.
     *
     * @param commandMap the commandMap to be forwarded.
     */
    public void onCommandReceived(HashMap<String, String> commandMap) {
        if(commandMap.get("COMMAND_TYPE").equals("PONG")) {
            pingController.onPongReceived(commandMap.get("NICKNAME"));
        } else {
            //TODO: sends commandMap to CommandParser
        }

    }

    /**
     * Handles the disconnection of a client.
     *
     * @param clientHandler the client disconnecting.
     */
    public void onDisconnect(ClientHandler clientHandler) {
        synchronized (lock) {
            String nickname = getNicknameFromClientHandler(clientHandler);

            if (nickname != null) {
                removeClient(nickname);
                broadcastConnectionMessage(nickname, false,false);

                if(clientHandlerMap.isEmpty()){
                    //TODO : end the game
                }
            }
        }
    }

    /**
     * Handles the disconnection or reconnection of a client.
     *
     * @param nickname the client disconnecting or reconnection
     * @param reconnection true if the client has reconnected to the game
     * @param connectionLost true if the client hasn't responded to a PING message
     *                       false if the client has been disconnected from the game
     */
    private void broadcastConnectionMessage(String nickname, boolean reconnection, boolean connectionLost) {
        synchronized (lock) {
            for (ClientHandler clientHandler: clientHandlerMap.values()) {
                clientHandler.sendConnectionMessage(nickname, reconnection, connectionLost);
            }
        }
    }

    /**
     * Sends a PING message to a specific client.
     *
     * @param nickname the nickname of the client to send the PING message to.
     */
    public void sendPingTo(String nickname) {
        clientHandlerMap.get(nickname).sendPing();
    }

    /**
     * Notifies the server that a PING message sent to a client has failed.
     *
     * @param nickname the nickname of the client that failed to respond to the PING message.
     */
    public void notifyPingFailure(String nickname) {
        clientHandlerMap.get(nickname).setConnected(false);
        broadcastConnectionMessage(nickname, false, true);
    }

    /**
     * Notifies the server of a reconnection event for a specific client.
     *
     * @param nickname the nickname of the client that has reconnected.
     */
    public void notifyReconnection(String nickname) {
        clientHandlerMap.get(nickname).setConnected(true);
        broadcastConnectionMessage(nickname, true, false);
    }

    /**
     * Returns the corresponding nickname of a ClientHandler.
     *
     * @param clientHandler the client handler.
     * @return the corresponding nickname of a ClientHandler.
     */
    public String getNicknameFromClientHandler(ClientHandler clientHandler) {
        return clientHandlerMap.entrySet()
                .stream()
                .filter(entry -> clientHandler.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public PingController getPingController() {
        return pingController;
    }

    /**
     * Checks if a specific client is connected.
     *
     * @param nickname the nickname of the client to check.
     * @return true if the client is connected, false otherwise.
     */
    public boolean isConnected(String nickname) {
        return clientHandlerMap.get(nickname).isConnected();
    }

    public Map<String, ClientHandler> getClientHandlerMap() {
        return clientHandlerMap;
    }
}