package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersRepresentation extends VirtualModelSubject {
    private final Map<String, PlayerMTC> PLAYER_MESSAGES;

    private static PlayersRepresentation instance;

    private PlayersRepresentation() {
        super();
        PLAYER_MESSAGES = new HashMap<>();
    }

    public static PlayersRepresentation getInstance() {
        if (instance == null) instance = new PlayersRepresentation();
        return instance;
    }

    public ArrayList<String> getPlayersList() {
        return PLAYER_MESSAGES.values().stream().map(PlayerMTC::getNickname).collect(Collectors.toCollection(ArrayList::new));
    }

    public int getPlayerScore(String nickname) {
        return PLAYER_MESSAGES.get(nickname).getScore();
    }

    public PlayerMTC getPlayerByNickname(String nickname) {
        return PLAYER_MESSAGES.get(nickname);
    }

    /**
     * adds a player or updates it with the new attributes sent by the server
     */
    public void updatePlayer(PlayerMTC playerMessage) {
        String nickname = playerMessage.getNickname();
        if (!playerMessage.isConnected()) {
            System.out.println("Player " + nickname + " disconnected");
            PLAYER_MESSAGES.remove(nickname);
            if (SocketClient.getInstance().getNickname().equals(nickname)) {
                System.exit(0);
            }
        } else PLAYER_MESSAGES.put(nickname, playerMessage);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(VirtualModelObserver::update);
    }
}