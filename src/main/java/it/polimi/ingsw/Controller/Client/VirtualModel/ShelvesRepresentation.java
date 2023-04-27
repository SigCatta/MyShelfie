package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.ShelfMessage;

import java.util.HashMap;
import java.util.Map;

public class ShelvesRepresentation implements VirtualModelSubject {
    /**
     * nickname of the player that owns the shelf
     */
    private final Map<String, ShelfMessage> SHELF_MESSAGES = new HashMap<>();
    private static ShelvesRepresentation instance;

    private ShelvesRepresentation() {}

    public static ShelvesRepresentation getInstance() {
        if (instance == null) instance = new ShelvesRepresentation();
        return instance;
    }

    /**
     * updates the shelf with the new one sent by the server
     */
    public void updateShelf(ShelfMessage shelfMessage) {
        String nickname = shelfMessage.getOwner();
        SHELF_MESSAGES.put(nickname, shelfMessage);
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

    }
}