package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.Controller.Server.VirtualView.Messages.PlayerMessage;
import java.util.HashMap;
import java.util.Map;

public class PlayersRepresentation extends SingletonImplementation implements VirtualModelSubject{

    private final Map<String, PlayerMessage> PLAYER_MESSAGES = new HashMap<>();

    private PlayersRepresentation() {}

    public static PlayersRepresentation getInstance() {
        return getInstance(PlayersRepresentation.class);
    }

    /**
     * adds a player or updates it with the new attributes sent by the server
     */
    public void updatePlayer(PlayerMessage playerMessage){
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