package it.polimi.ingsw.Controller.Client.ClientController;

import it.polimi.ingsw.Controller.Server.VirtualView.Messages.Message;

public interface Visitor {
    /**
     * starts double dispatch with the Message class
     * @param message, the message coming from the network
     */
    void updateVirtualModel(Message message);
}
