package it.polimi.ingsw.Controller.Server.VirtualView.Messages;

/**
 * the classes that will be sent using the socket must implement this interface
 */
public interface Message {
    /**
     * updates the virtual model when called
     */
    void update();
}
