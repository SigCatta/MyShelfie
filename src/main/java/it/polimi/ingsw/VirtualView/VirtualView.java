package it.polimi.ingsw.VirtualView;

import it.polimi.ingsw.VirtualView.Messages.MessageToClient;
import it.polimi.ingsw.VirtualView.ModelObservers.BoardVV;
import it.polimi.ingsw.VirtualView.ModelObservers.ChosenTilesTableVV;
import it.polimi.ingsw.VirtualView.ModelObservers.CommonGoalVV;
import it.polimi.ingsw.VirtualView.ModelObservers.GameVV;
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
        new GameVV(GAME, this); //the user needs this information even before the beginning of the game
        new BoardVV(GAME, this); // links the board observer to the board
        new CommonGoalVV(GAME, this);
        new ChosenTilesTableVV(GAME, this);
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
