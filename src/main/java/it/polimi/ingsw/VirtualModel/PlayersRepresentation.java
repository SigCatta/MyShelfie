package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Virtual model representation of the players
 */
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

    /**
     * Returns a list of the player's nicknames
     */
    public ArrayList<String> getPlayersList() {
        return PLAYER_MESSAGES.values().stream().map(PlayerMTC::getNickname).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a player's score
     *
     * @param nickname the player's nickname
     */
    public int getPlayerScore(String nickname) {
        return PLAYER_MESSAGES.get(nickname).getScore();
    }

    /**
     * Returns a player's {@link PlayerMTC}
     *
     * @param nickname the player's nickname
     */
    public PlayerMTC getPlayerInfoByNickname(String nickname) {
        return PLAYER_MESSAGES.get(nickname);
    }

    /**
     * Returns a list of all {@link PlayerMTC}
     */
    public ArrayList<PlayerMTC> getAllPlayerMTC() {
        return getPlayersList().stream().map(this::getPlayerInfoByNickname).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Adds a player or updates it with the new attributes sent by the server
     */
    public void updatePlayer(PlayerMTC playerMessage) {
        String nickname = playerMessage.getNickname();
        if (!playerMessage.isConnected()) System.out.println("Player " + nickname + " disconnected");

        PLAYER_MESSAGES.put(nickname, playerMessage);
        notifyObservers();
    }
}