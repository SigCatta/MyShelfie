package it.polimi.ingsw.Controller.Client.ClientController;

import it.polimi.ingsw.View.VirtualView.Messages.MessageToClient;

public interface Visitor {
    /**
     * starts double dispatch with the Message class
     * @param messageToClient, the message coming from the network
     */
    void visit(MessageToClient messageToClient);
}
