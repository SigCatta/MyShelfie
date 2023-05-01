package it.polimi.ingsw.Controller.Server.ServerController;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Server.Executor.*;

public class ServerController implements ServerVisitor {
    private static ServerController instance;

    private ServerController(){}

    public static ServerController getInstance(){
        if(instance == null) instance = new ServerController();
        return instance;
    }

    @Override
    public void visit(MessageToServer message) {
        message.update();
    }

    /**
     * get the tiles from the board passing through the games manager
     */
    public void pickUpTiles(MessageToServer message){
        PickupTilesExecutor.execute(message);
    }

    /**
     * removes the player from a game permanently
     */
    public void bye(MessageToServer message){
        ByeExecutor.execute(message);
    }

    public void newGame(MessageToServer message){
        NewGameExecutor.execute((NewGameMessage) message);
    }

    /**
     * the player joins an existing game
     */
    public void canIPlay(MessageToServer message){
        CanIPlayExecutor.execute((CanIPlayMessage) message);
    }

    /**
     * push the message into the chat
     */
    public void chat(MessageToServer message){
        ChatExecutor.execute(message);
    }

    /**
     * Insert the tiles in the shelf
     */
    public void insertTiles(MessageToServer message){
        InsertTilesExecutor.execute(message);
    }

    public void handshake(MessageToServer message){
        HandshakeExecutor.execute((HandshakeMessage) message);
    }

    public void pongMessage(MessageToServer message){
        message.getSocketClientHandler().onPongReceived();
    }

}
