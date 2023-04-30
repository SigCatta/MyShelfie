package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.PlayerMessageToClient;

import java.util.HashMap;
import java.util.Map;

public class PlayersRepresentation implements VirtualModelSubject{

    private final Map<String, PlayerMessageToClient> PLAYER_MESSAGES = new HashMap<>();

    private static PlayersRepresentation instance;

    private PlayersRepresentation() {}

    public static PlayersRepresentation getInstance() {
        if (instance == null) instance = new PlayersRepresentation();
        return instance;
    }

    public PlayerMessageToClient getPlayerByNickname(String nickname){
        return PLAYER_MESSAGES.get(nickname);
    }

    /**
     * adds a player or updates it with the new attributes sent by the server
     */
    public void updatePlayer(PlayerMessageToClient playerMessage){
        String nickname = playerMessage.getNickname();
        PLAYER_MESSAGES.put(nickname, playerMessage);
        notifyObservers();
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