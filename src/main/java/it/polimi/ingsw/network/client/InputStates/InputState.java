package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.View.CLI.Printer;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelSubject;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.InputStates.readers.Reader;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public abstract class InputState {
    String input;
    final Client socketClient = SocketClient.getInstance();
    final InputStatePlayer player;

    InputState(InputStatePlayer player) {
        this.player = player;
    }

    public abstract void play();

    void getInput() {
        try {
            input = readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized void waitForVM(VirtualModelSubject representation) {
        try {
            representation.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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
        if (message.getID() == EchoID.CHANGE) Printer.printHomeScreen();
        else System.out.println(message.getOutput());
    }
}
