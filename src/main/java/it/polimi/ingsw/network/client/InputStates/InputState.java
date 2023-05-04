package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.InputReader;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public abstract class InputState {
    String input;
    Client socketClient = SocketClient.getInstance();
    InputReader reader;

    InputState(InputReader reader){
        this.reader = reader;
    }
    public abstract void play();
    void getInput() {
        try {
            input = readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
