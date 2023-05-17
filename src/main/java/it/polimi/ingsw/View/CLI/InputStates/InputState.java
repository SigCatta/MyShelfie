package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelSubject;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;

public abstract class InputState {
    String input;
    final Client socketClient = SocketClient.getInstance();

    public abstract void play();

    /**
     * Helper method to wait on an element of the virtual model
     *
     * @param representation the element on which to wait
     */
    synchronized void waitForVM(VirtualModelSubject representation) {
        try {
            representation.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method waits on EchoRepresentation, when it gets notified checks if
     * the user is using a commands, if that is the case, the methods waits for
     * the user to finish, then prints an updated home screen
     *
     * @param reader the reader that user is using
     */
    void addReaderToEchoObserver(Reader reader) {
        EchosRepresentation.getInstance().registerObserver(reader);
    }
}
