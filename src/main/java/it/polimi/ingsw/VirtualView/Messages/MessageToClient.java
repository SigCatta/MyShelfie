package it.polimi.ingsw.VirtualView.Messages;

/**
 * the classes that will be sent using the socket must implement this interface
 */
public interface MessageToClient {
    /**
     * updates the virtual model when called
     */
    void update();
}
