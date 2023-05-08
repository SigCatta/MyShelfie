package it.polimi.ingsw.network.client.InputStates.readers;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public abstract class Reader {
    String input;
    boolean isReading;
public abstract void run();
public abstract void executeCommand();
    void getInput() {
        try {
            input = readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isReading(){
        return isReading;
    }

}
