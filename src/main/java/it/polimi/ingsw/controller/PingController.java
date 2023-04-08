package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.server.Server;

import java.util.*;

public class PingController {

    private Server server;
    private Timer timer;
    /**
     * the timeout in milliseconds
     */
    private int pingTimeout;
    /**
     * a map tha contains the nicknames of the clients connected to the game as keys
     * and a boolean as value, that tells wether a PONG message has been received from the client
     */
    private Map<String, Boolean> receivedPongMap;

    public PingController(Server server, int pingTimeout) {
        this.server = server;
        this.timer = new Timer();
        this.receivedPongMap = Collections.synchronizedMap(new HashMap<>());
        this.pingTimeout = pingTimeout;
    }

    public void addToPongMap(String nickname) {
        receivedPongMap.put(nickname, true);
    }

    public void removeFromPongMap(String nickname) {
        receivedPongMap.remove(nickname);
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (String nickname: receivedPongMap.keySet()) {
                    if (receivedPongMap.get(nickname)) {
                        // send PING message only if the corresponding PONG has been received
                        server.sendPingTo(nickname);
                        receivedPongMap.replace(nickname, false);
                    } else {
                        // if no PONG message has been received notify the sever
                        server.notifyPingFailure(nickname);
                    }
                }

            }
        }, 0, pingTimeout); // sends a PING message every pingTimeout ms
    }

    /**
     * Method called by Executor when it receives a PONG message from the client with nickname = {@param nickname}
     */
    public void receivePong(String nickname) {
        if(!server.isConnected(nickname)) {
            server.notifyReconnection(nickname);
        }
        receivedPongMap.replace(nickname, true);
    }

}