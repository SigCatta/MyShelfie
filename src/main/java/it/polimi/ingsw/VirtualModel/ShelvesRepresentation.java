package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.ShelfMTC;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.HashMap;
import java.util.Map;

public class ShelvesRepresentation extends VirtualModelSubject {
    private final Map<String, ShelfMTC> SHELF_MESSAGES;
    private static ShelvesRepresentation instance;

    private ShelvesRepresentation() {
        super();
        SHELF_MESSAGES = new HashMap<>();
    }

    public static ShelvesRepresentation getInstance() {
        if (instance == null) instance = new ShelvesRepresentation();
        return instance;
    }

    /**
     * updates the shelf with the new one sent by the server
     */
    public void updateShelf(ShelfMTC shelfMessage) {
        String nickname = shelfMessage.getOwner();
        SHELF_MESSAGES.put(nickname, shelfMessage);
        if (SocketClient.getInstance().getNickname().equals(nickname)) notifyObservers();
    }

    public ShelfMTC getShelfMessage(String nickname) {
        return SHELF_MESSAGES.get(nickname);
    }

    //TODO method that retrieves the players

    @Override
    public void notifyObservers() {
       observers.forEach(VirtualModelObserver::update);
    }
}