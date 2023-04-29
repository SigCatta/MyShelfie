package it.polimi.ingsw.Controller.Client.ClientController;

import it.polimi.ingsw.Controller.Client.Mappers.PongMapper;
import it.polimi.ingsw.Controller.Client.VirtualModel.*;
import it.polimi.ingsw.View.VirtualView.Messages.*;
import it.polimi.ingsw.network.client.SocketClient;

/**
 * Visitor singleton class that is used to get the right action based on the type of command
 */
public class Controller implements Visitor{

    private static  Controller controllerInstance;

    private Controller(){}

    public static Controller getInstance(){
        if(controllerInstance == null) controllerInstance = new Controller();
        return controllerInstance;
    }

    /**
     * using double dispatch this method calls the update method in message
     * that calls the right action to be performed
     */
    @Override
    public void updateVirtualModel(Message message){
        message.update();
    }
    /**
     * changes the information regarding the board
     */
    public void changeBoard(Message board){
        BoardRepresentation.getInstance().setBoard((BoardMessage) board);
    }
    /**
     * changes the information regarding the shelf
     */
    public void changeShelf(Message shelf){
        ShelvesRepresentation.getInstance().updateShelf((ShelfMessage) shelf);
    }

    /**
     * changes the information regarding the game
     */
    public void changeGame(Message game){
        GameRepresentation.getInstance().setGame((GameMessage) game);
    }
    /**
     * changes the information regarding the player
     */
    public void changePlayer(Message player){
        PlayersRepresentation.getInstance().updatePlayer((PlayerMessage) player);
    }

    public void changeTilesTable(Message table){

    }

    /**
     * reacts on ping received sending a pong message
     */
    public void ping(Message ping){
        SocketClient.getInstance().sendCommand(PongMapper.getMap());
    }

    /**
     * reacts on error received
     */
    public void error(Message error){
        ErrorsRepresentation.getInstance().putError((ErrorMessage) error);
    }

    /**
     * push the incoming chat message into the virtual view
     */
    public void chat(Message chat){
        ChatRepresentation.getInstance().addMessage((ChatMessage) chat);
    }

}