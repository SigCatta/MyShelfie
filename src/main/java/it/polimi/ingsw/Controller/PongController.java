package it.polimi.ingsw.Controller;

import it.polimi.ingsw.network.client.Client;

import java.util.*;

public class PongController {

    private final Client client;

    /**
     Creates a new PongController instance.
     *
     * @param client the client instance.
     */
    public PongController(Client client) {
        this.client = client;
    }

    /**
     * Method called by SocketClient when it receives a PING message is received from the server
     *
     */
    public void onPingReceived() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("NICKNAME", client.getNickname());
        commandMap.put("GAME_ID", String.valueOf(client.getGameId()));
        commandMap.put("COMMAND_TYPE", "PONG");
        Client.LOGGER.info("PONG sent to the server");
        client.sendCommand(commandMap);
    }
}