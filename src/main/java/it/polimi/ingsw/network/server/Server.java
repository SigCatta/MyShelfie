package it.polimi.ingsw.network.server;

import it.polimi.ingsw.Controller.PingController;
import it.polimi.ingsw.Controller.Server.CommandParser;

import java.util.*;
import java.util.logging.Logger;

/**
 * Server class that generates a socket server.
 */
public class Server {
    CommandParser commandParser;
    /**
     * clientHandlerMap: [ key: (String) gameId --> values: (List<ClientHandler>) clientHandlerList]
     */
    private final Map<String, List<ClientHandler>> clientHandlerMap;
    /**
     * Every nickname must be used by one and only one players
     */
    private final List<String> nicknameList;
    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private final Object lock;

    PingController pingController;

    private static Server server_instance = null;

    public Server(int pingTimeout) {
        this.nicknameList = new ArrayList<>();
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
    public void addClient(String nickname, ClientHandler clientHandler, HashMap<String, String> commandMap) {
        int gameId = Integer.parseInt(commandMap.get("GAMEID"));
        List<ClientHandler> clientHandlerList = clientHandlerMap.getOrDefault(String.valueOf(gameId), new ArrayList<>());

        if (!nicknameList.contains(nickname)) {
            nicknameList.add(nickname);
            clientHandlerList.add(clientHandler);
            clientHandlerMap.put(String.valueOf(gameId), clientHandlerList);

            if(pingController.getClientMap().containsKey(nickname)) {
                if(!pingController.getClientMap().get(nickname) &&
                        pingController.getGameIdMap().get(nickname) == Integer.parseInt(commandMap.get("GAMEID"))) {
                    //this client was already connected to an existing game and wants to reconnect
                    pingController.getClientMap().replace(nickname, true);
                    notifyReconnection(nickname, clientHandler.getGameId());
                    return;
                } else {
                    pingController.removeFromClientMap(nickname);
                }
            }

            pingController.addToClientMap(nickname, clientHandler.getGameId());

            Server.LOGGER.info(nickname + " added to the game " + commandMap.get("GAMEID"));
            commandParser.parse(commandMap);
        } else {
            //nickname already taken
        }
    }

    /**
     * Removes a client given his nickname.
     *
     * @param clientHandler  the nickname of the client to be removed
     */
    public void removeClient(ClientHandler clientHandler) {
        clientHandlerMap.get(String.valueOf(clientHandler.getGameId())).remove(clientHandler);
        pingController.getClientMap().replace(clientHandler.getNickname(), false);
        LOGGER.info(() -> "Removed " + clientHandler.getNickname() + " from the client list.");
    }

    /**
     * Forwards a received commandMap from the client to the GameController.
     *
     * @param commandMap the commandMap to be forwarded.
     */
    public void onCommandReceived(HashMap<String, String> commandMap) {
        if(commandMap.get("COMMAND").equals("PONG")) {
            pingController.onPongReceived(commandMap.get("NICKNAME"), Integer.parseInt(commandMap.get("GAMEID")));
        } else {
            commandParser.parse(commandMap);
        }

    }

    /**
     * Handles the disconnection of a client.
     *
     * @param clientHandler the client disconnecting.
     */
    public void onDisconnect(ClientHandler clientHandler) {
        String nickname = clientHandler.getNickname();
        int gameIdToCheck = clientHandler.getGameId();

        if (nickname != null) {
            synchronized (lock) {
                removeClient(clientHandler);
                broadcastConnectionMessage(nickname, gameIdToCheck, false, false);

                if(clientHandlerMap.get(String.valueOf(gameIdToCheck)).size()==0) {
                    //the game with gameId==gameIdToCheck must end
                    clientHandlerMap.remove(String.valueOf(gameIdToCheck));
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
            for (ClientHandler clientHandler: clientHandlerMap.get(String.valueOf(gameId))) {
                    clientHandler.sendConnectionMessage(nickname, gameId, reconnection, connectionLost);
            }
        }
    }

    /**
     * Sends a PING message to a specific client.
     *
     * @param nickname the nickname of the client to send the PING message to.
     */
    public void sendPingTo(String nickname, int gameId) {
        ClientHandler clientHandler = getClientHandler(nickname, gameId);
        if(clientHandler != null)
            clientHandler.sendPing();
    }

    public ClientHandler getClientHandler (String nickname, int gameId) {
        List<ClientHandler> clientHandlerList = clientHandlerMap.get(String.valueOf(gameId));

        for (ClientHandler c : clientHandlerList) {
            if(c.getNickname().equals(nickname)) {
                return c;
            }
        }
        return  null;
    }

    /**
     * Notifies the server that a PING message sent to a client has failed.
     *
     * @param nickname the nickname of the client that failed to respond to the PING message.
     */
    public void notifyPingFailure(String nickname, int gameId) {
        ClientHandler clientHandler = getClientHandler(nickname, gameId);
        if(clientHandler != null)
            clientHandler.setConnected(false);

        broadcastConnectionMessage(nickname, gameId, false, true);

        //notify the model
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", nickname);
        commandMap.put("GAMEID", String.valueOf(gameId));
        commandMap.put("COMMAND", "PLAYER_DOWN");
        //TODO send commandMap to decoder

    }

    /**
     * Notifies the server of a reconnection event for a specific client.
     *
     * @param nickname the nickname of the client that has reconnected.
     */
    public void notifyReconnection(String nickname, int gameId) {
        Server.LOGGER.info(nickname + " reconnected");
        ClientHandler clientHandler = getClientHandler(nickname, gameId);
        if(clientHandler != null)
            clientHandler.setConnected(true);
        broadcastConnectionMessage(nickname, gameId, true, false);

        //notify the model
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", nickname);
        commandMap.put("COMMAND", "RECONNECT");
        //TODO send commandMap to decoder
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
    public boolean isConnected(String nickname, int gameId) {
        ClientHandler clientHandler = getClientHandler(nickname, gameId);
        if(clientHandler != null)
            return clientHandler.isConnected();
        return false;
    }

    public Map<String, List<ClientHandler>> getClientHandlerMap() {
        return clientHandlerMap;
    }
}