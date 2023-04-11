package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Controller.PingController;
import it.polimi.ingsw.Controller.Server.CommandParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Server class that generates a socket server.
 */
public class Server {
    CommandParser commandParser;
    private final Map<String, ClientHandler> clientHandlerMap;

    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private final Object lock;

    PingController pingController;

    private static Server server_instance = null;

    public Server(int pingTimeout) {
        this.commandParser = new CommandParser();
        this.clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        this.lock = new Object();
        this.pingController = new PingController(this, pingTimeout);
        server_instance = this;
    }

    public static synchronized Server getInstance() {
        return server_instance;
    }

    /**
     * Adds a client to be managed by the server instance.
     *
     * @param nickname      the nickname associated with the client.
     * @param clientHandler the ClientHandler associated with the client.
     */
    public void addClient(String nickname, ClientHandler clientHandler, Map<String, String> commandMap) {
        if (!clientHandlerMap.containsKey(nickname)) {
            clientHandlerMap.put(nickname, clientHandler);

            if(pingController.getClientMap().containsKey(nickname)) {
                if(!pingController.getClientMap().get(nickname) &&
                        pingController.getGameIdMap().get(nickname) == Integer.parseInt(commandMap.get("GAME_ID"))) {
                    //this client was already connected to an existing game and wants to reconnect
                    pingController.getClientMap().replace(nickname, true);
                    notifyReconnection(nickname, clientHandler.getGameId());
                    return;
                } else {
                    pingController.removeFromClientMap(nickname);
                }
            }

            pingController.addToClientMap(nickname, clientHandler.getGameId());
            Server.LOGGER.info(nickname + " added to the game " + commandMap.get("GAME_ID"));
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
        pingController.getClientMap().replace(nickname, false);
        LOGGER.info(() -> "Removed " + nickname + " from the client list.");
    }

    /**
     * Forwards a received commandMap from the client to the GameController.
     *
     * @param commandMap the commandMap to be forwarded.
     */
    public void onCommandReceived(HashMap<String, String> commandMap) {
        if(commandMap.get("COMMAND_TYPE").equals("PONG")) {
            pingController.onPongReceived(commandMap.get("NICKNAME"), Integer.parseInt(commandMap.get("GAME_ID")));
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
            int gameIdToCheck = clientHandler.getGameId();

            if (nickname != null) {
                removeClient(nickname);
                broadcastConnectionMessage(nickname, gameIdToCheck, false, false);

                boolean noMorePlayer = true;
                synchronized (lock) {
                    for(ClientHandler ch: clientHandlerMap.values()) {
                        if(ch.getGameId() == gameIdToCheck){
                            noMorePlayer = false;
                            break;
                        }
                    }
                }

                if(noMorePlayer){
                    //the game with gameId==gameIdToCheck must end
                    for(Map.Entry<String, Integer> entry: pingController.getGameIdMap().entrySet()) {
                        if(gameIdToCheck == entry.getValue()) {
                            pingController.removeFromClientMap(entry.getKey());
                        }
                    }
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
    private void broadcastConnectionMessage(String nickname, int gameId, boolean reconnection, boolean connectionLost) {
        synchronized (lock) {
            for (ClientHandler clientHandler: clientHandlerMap.values()) {
                if(clientHandler.getGameId() == gameId) {
                    clientHandler.sendConnectionMessage(nickname, gameId, reconnection, connectionLost);
                }
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
    public void notifyPingFailure(String nickname, int gameId) {
        clientHandlerMap.get(nickname).setConnected(false);
        broadcastConnectionMessage(nickname, gameId, false, true);

        //notify the model
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", nickname);
        commandMap.put("GAME_ID", String.valueOf(gameId));
        commandMap.put("COMMAND_TYPE", "PLAYER_DOWN");
        //TODO send commandMap to decoder

    }

    /**
     * Notifies the server of a reconnection event for a specific client.
     *
     * @param nickname the nickname of the client that has reconnected.
     */
    public void notifyReconnection(String nickname, int gameID) {
        Server.LOGGER.info(nickname + " reconnected");
        clientHandlerMap.get(nickname).setConnected(true);
        broadcastConnectionMessage(nickname, gameID, true, false);

        //notify the model
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", nickname);
        commandMap.put("COMMAND_TYPE", "RECONNECT");
        //TODO send commandMap to decoder
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