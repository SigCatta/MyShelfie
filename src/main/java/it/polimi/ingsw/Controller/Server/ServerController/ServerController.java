package it.polimi.ingsw.Controller.Server.ServerController;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Server.Executor.*;
import it.polimi.ingsw.model.Game;

public class ServerController implements ServerVisitor {
    private static ServerController instance;

    private ServerController(){}

    public static ServerController getInstance(){
        if(instance == null) instance = new ServerController();
        return instance;
    }

    @Override
    public void visit(MessageToServer message, Game game) {
        message.update(game);
    }

    /**
     * get the tiles from the board passing through the games manager
     */
    public void pickUpTiles(MessageToServer message, Game game){
        new PickupTilesExecutor(game).execute(message);
    }

    /**
     * removes the player from a game permanently
     */
    public void bye(MessageToServer message, Game game){
        new ByeExecutor(game).execute(message);
    }

    public void newGame(MessageToServer message){
        new NewGameExecutor(null).execute(message);
    }

    /**
     * the player joins an existing game
     */
    public void canIPlay(MessageToServer message, Game game){
        new CanIPlayExecutor(game).execute(message);
    }

    /**
     * push the message into the chat
     */
    public void chat(MessageToServer message, Game game){
        new ChatExecutor(game).execute(message);
    }

    /**
     * Insert the tiles in the shelf
     */
    public void insertTiles(MessageToServer message, Game game){
        new InsertTilesExecutor(game).execute(message);
    }


}
