package it.polimi.ingsw.View.VirtualView;

import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.View.VirtualView.ModelObservers.BoardView;
import it.polimi.ingsw.View.VirtualView.ModelObservers.ChosenTilesTableView;
import it.polimi.ingsw.View.VirtualView.ModelObservers.GameView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.ArrayList;
import java.util.List;

public class VirtualView {

    private List<SocketClientHandler> clientHandlers;
    private final Game GAME;

    public void addClient(SocketClientHandler clientHandler){
        clientHandlers.add(clientHandler);
    }

    public VirtualView(Game game){
        this.GAME = game;
        clientHandlers = new ArrayList<>();
        new GameView(GAME, this); //the user needs this information even before the beginning of the game
        new BoardView(GAME, this); // links the board observer to the board
        new ChosenTilesTableView(GAME, this);
    }

    public void send(MessageToClient messageToClient){
        for(SocketClientHandler clientHandler : clientHandlers){
            clientHandler.sendCommand(messageToClient);
        }
    }

    public void updateAllPlayers(){
        GAME.getPlayers().forEach(Player::notifyObservers);
    }
}
