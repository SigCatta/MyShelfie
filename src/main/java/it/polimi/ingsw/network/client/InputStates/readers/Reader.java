package it.polimi.ingsw.network.client.InputStates.readers;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public abstract class Reader {
    String input;
    boolean isReading;
public abstract void run();
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

    void chooseCommand(){
        getInput();

        isReading = true;
        input = input.toLowerCase();
        CommandExecutorFactory.getCommand(input).execute();

        isReading = false;
        synchronized (this) {
            notifyAll();
        }
    }

}
