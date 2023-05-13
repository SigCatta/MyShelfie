package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersRepresentation implements VirtualModelSubject {

    private final Map<String, PlayerMTC> PLAYER_MESSAGES = new HashMap<>();

    private static PlayersRepresentation instance;

    private PlayersRepresentation() {
    }

    public static PlayersRepresentation getInstance() {
        if (instance == null) instance = new PlayersRepresentation();
        return instance;
    }

    public ArrayList<String> getPlayersList() {
        return PLAYER_MESSAGES.values().stream().map(PlayerMTC::getNickname).collect(Collectors.toCollection(ArrayList::new));
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

    //TODO method that retrieves the players

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {
        //TODO every observer must be notified when the class changes
    }
}