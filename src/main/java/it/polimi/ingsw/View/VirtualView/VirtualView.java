package it.polimi.ingsw.View.VirtualView;

import it.polimi.ingsw.View.VirtualView.Messages.Message;
import it.polimi.ingsw.View.VirtualView.ModelObservers.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.util.ArrayList;
import java.util.List;

public class VirtualView {

    private final List<SocketClientHandler> CLIENT_HANDLERS;
    private final Game GAME;

    public void addClient(SocketClientHandler clientHandler){
        CLIENT_HANDLERS.add(clientHandler);
    }

    public VirtualView(Game game){
        this.GAME = game;
        CLIENT_HANDLERS = new ArrayList<>();
    }

    /**
     * creates all the necessary observers and make them observe the respective objects
     */
    public void observersInit(){

        GAME.registerObserver(new GameView(GAME, this));
        GAME.getBoard().registerObserver(new BoardView(GAME, this)); // links the board observer to the board
        for(Player player : GAME.getPlayers()){
            player.registerObserver(new PlayerView(player, this));// links the player observer to the player
            player.getShelf().registerObserver(new ShelfView(player, this));
        }
        GAME.getTilesGetter().getChosenTilesTable().registerObserver(new ChosenTilesTableView(GAME, this));
    }

    public void send(Message message){
        for(SocketClientHandler clientHandler : CLIENT_HANDLERS){
            clientHandler.sendCommand(message);
        }
    }
}
