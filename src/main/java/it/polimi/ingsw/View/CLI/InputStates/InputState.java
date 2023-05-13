package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelSubject;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
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
     * @param reader the reader that user might be using
     */
    void runInputReaderUntilModelUpdate(Reader reader) {
        synchronized (EchosRepresentation.getInstance()) {
            waitForVM(EchosRepresentation.getInstance());
        }

        while (reader.isReading()) { // if the user is using a command the view does not update
            synchronized (reader) {
                try {
                    reader.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        EchoMTC message = EchosRepresentation.getInstance().getMessage();
        if (message.getID() == EchoID.CHANGE) {
            Printer.clearConsole();
            Printer.printHomeScreen();
        } else {
            System.out.println(message.getOutput());
            if (message.isError()) {
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(700);
                        System.out.println(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Printer.printHomeScreen();
            }
        }
    }
}
