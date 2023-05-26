package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.VirtualView.Messages.ShelfMTC;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Virtual model representation of the shelves
 */
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
     * Updates the shelf with the new one sent by the server
     */
    public void updateShelf(ShelfMTC shelfMessage) {
        String nickname = shelfMessage.getOwner();
        SHELF_MESSAGES.put(nickname, shelfMessage);
        if (SocketClient.getInstance().getNickname().equals(nickname)) notifyObservers();
    }

    /**
     * Returns a player's shelf
     *
     * @param nickname the player's nickname
     */
    public ShelfMTC getShelfMessage(String nickname) {
        return SHELF_MESSAGES.get(nickname);
    }
}