package it.polimi.ingsw.Controller;

import it.polimi.ingsw.network.server.Server;

import java.util.*;

public class PingController {

    private final Server server;
    private final Timer timer;
    /**
     * the timeout in milliseconds
     */
    private final int pingTimeout;
    /**
     * A map that contains the nicknames of the clients connected to the game as keys
     * and a boolean as value, that tells whether a PONG message has been received from the client.
     */
    private final Map<String, Boolean> clientMap;
    /**
     * A map that contains the nicknames of the clients connected to the game as keys
     * and the corresponding gameID.
     */
    private final Map<String, Integer> gameIdMap;

    /**
     Creates a new PingController instance.
     *
     * @param server the server instance.
     * @param pingTimeout the timeout in milliseconds.
     */
    public PingController(Server server, int pingTimeout) {
        this.server = server;
        this.timer = new Timer();
        this.clientMap = Collections.synchronizedMap(new HashMap<>());
        this.gameIdMap = Collections.synchronizedMap(new HashMap<>());
        this.pingTimeout = pingTimeout;
    }

    public void addToClientMap(String nickname, int gameID) {
        gameIdMap.put(nickname, gameID);
        clientMap.put(nickname, true);
    }

    public void removeFromClientMap(String nickname) {
        gameIdMap.remove(nickname);
        clientMap.remove(nickname);
    }

    /**
     * Starts the ping-pong communication.
     */
    public void start() {
        Server.LOGGER.info("PingController started");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (String nickname: clientMap.keySet()) {
                    synchronized (clientMap) {
                        if (clientMap.get(nickname)) {
                            // send PING message only if the corresponding PONG has been received
                            server.sendPingTo(nickname);
                            Server.LOGGER.info("PING sent to "+ nickname);
                            clientMap.replace(nickname, false);
                        } else {
                            // if no PONG message has been received notify the sever
                            Server.LOGGER.severe("No PONG received from "+ nickname);
                            server.notifyPingFailure(nickname, gameIdMap.get(nickname));
                        }
                    }
                }

            }
        }, 500, pingTimeout); // sends a PING message every pingTimeout ms
    }

    /**
     * Method called by the Server when it receives a PONG message from the client with nickname = {@param nickname}.
     *
     @param nickname the nickname of the client that sent the PONG message.
     */
    public void onPongReceived(String nickname, int gameId) {
        clientMap.replace(nickname, true);
        if(!server.isConnected(nickname)) {
            server.notifyReconnection(nickname, gameId);
        }
    }

    public Map<String, Boolean> getClientMap() {
        return clientMap;
    }

    public Map<String, Integer> getGameIdMap() {
        return gameIdMap;
    }
}