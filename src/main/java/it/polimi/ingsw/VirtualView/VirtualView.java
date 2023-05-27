package it.polimi.ingsw.VirtualView;

import it.polimi.ingsw.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.VirtualView.ModelObservers.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the virtual view of the game. It is responsible for managing communication between
 * the game model and the client handlers.
 */
public class VirtualView {

    private final List<SocketClientHandler> clientHandlers;
    private final Game GAME;

    /**
     * Constructs a VirtualView instance for the specified game.
     *
     * @param game The game associated with the virtual view.
     */
    public VirtualView(Game game) {
        this.GAME = game;
        clientHandlers = new ArrayList<>();
        new GameVV(GAME, this); // The user needs this information even before the beginning of the game
        new BoardVV(GAME, this); // Links the board observer to the board
        new CommonGoalVV(GAME, this);
        new ChosenTilesTableVV(GAME, this);
    }

    /**
     * Adds a client handler to the virtual view.
     *
     * @param clientHandler The client handler to add.
     */
    public void addClient(SocketClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
    }

    /**
     * Retrieves the client handler associated with the specified nickname.
     *
     * @param nickname The nickname of the client.
     * @return The client handler associated with the nickname, or null if not found.
     */
    public SocketClientHandler getSocketHandlerClientByNickname(String nickname) {
        return clientHandlers.stream().filter(ch -> ch.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    /**
     * Sends a message to all connected clients.
     *
     * @param messageToClient The message to send.
     */
    public void send(MessageToClient messageToClient) {
        for (SocketClientHandler clientHandler : clientHandlers) {
            clientHandler.sendCommand(messageToClient);
        }
    }

    /**
     * Updates all players by notifying their observers.
     */
    public void updateAllPlayers() {
        GAME.getPlayers().forEach(Player::notifyObservers);
    }

    /**
     * Updates all shelves by notifying their observers.
     */
    public void updateAllShelves() {
        GAME.getPlayers().forEach(p -> p.getShelf().notifyObservers());
    }

    /**
     * Updates all common goals by notifying their observers.
     */
    public void updateAllCommonGoals() {
        GAME.getCommonGoals().forEach(ModelSubject::notifyObservers);
    }
}