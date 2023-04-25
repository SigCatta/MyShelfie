package it.polimi.ingsw.Controller.Client.ClientController;

import it.polimi.ingsw.View.VirtualView.Messages.Message;

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

    @Override
    public void updateVirtualModel(Message message){
        message.update();
    }
    /**
     * changes the information regarding the board
     */
    public void changeBoard(Message board){
        //TODO
    }
    /**
     * changes the information regarding the shelf
     */
    public void changeShelf(Message shelf){
        //TODO
    }

    /**
     * changes the information regarding the game
     */
    public void changeGame(Message game){
        //TODO
    }
    /**
     * changes the information regarding the player
     */
    public void changePlayer(Message player){
        //TODO
    }

}
