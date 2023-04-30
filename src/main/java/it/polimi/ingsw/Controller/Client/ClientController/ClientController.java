package it.polimi.ingsw.Controller.Client.ClientController;

import it.polimi.ingsw.Controller.Client.Mappers.PongMapper;
import it.polimi.ingsw.Controller.Client.VirtualModel.*;
import it.polimi.ingsw.View.VirtualView.Messages.*;
import it.polimi.ingsw.network.client.SocketClient;

/**
 * Visitor singleton class that is used to get the right action based on the type of command
 */
public class ClientController implements Visitor{

    private static ClientController clientControllerInstance;

    private ClientController(){}

    public static ClientController getInstance(){
        if(clientControllerInstance == null) clientControllerInstance = new ClientController();
        return clientControllerInstance;
    }

    /**
     * using double dispatch this method calls the update method in message
     * that calls the right action to be performed
     */
    @Override
    public void visit(MessageToClient messageToClient){
        messageToClient.update();
    }
    /**
     * changes the information regarding the board
     */
    public void changeBoard(MessageToClient board){
        BoardRepresentation.getInstance().setBoard((BoardMessageToClient) board);
    }

    public void changeCommonGoal(MessageToClient commonGoal){
        CommonGoalsRepresentation.getInstance().changeCommonGoal((CommonGoalMessage) commonGoal);
    }
    /**
     * changes the information regarding the shelf
     */
    public void changeShelf(MessageToClient shelf){
        ShelvesRepresentation.getInstance().updateShelf((ShelfMessageToClient) shelf);
    }

    /**
     * changes the information regarding the game
     */
    public void changeGame(MessageToClient game){
        GameRepresentation.getInstance().setGame((GameMessageToClient) game);
    }
    /**
     * changes the information regarding the player
     */
    public void changePlayer(MessageToClient player){
        PlayersRepresentation.getInstance().updatePlayer((PlayerMessageToClient) player);
    }

    public void changeTilesTable(MessageToClient table){

    }

    /**
     * reacts on ping received sending a pong message
     */
    public void ping(MessageToClient ping){
        SocketClient.getInstance().sendCommand(PongMapper.getMap());
    }

    /**
     * reacts on error received
     */
    public void error(MessageToClient error){
        ErrorsRepresentation.getInstance().putError((ErrorMessageToClient) error);
    }

    /**
     * push the incoming chat message into the virtual view
     */
    public void chat(MessageToClient chat){
        ChatRepresentation.getInstance().addMessage((ChatMessageToClient) chat);
    }

}
