package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

/**
 * each class that implements this interface is going to be mapping a give stack of parameters to the correct command
 * and sending it to the client socket
 */
public interface ClientMappable {
    /**
     * sends a command map to the socket based on the given parameters
     *
     * @param strings a stack of parameters that will be mapped togather with the corresponding command and sent to the socket
     */
    HashMap<String, String> map (Stack<String> strings);
}
