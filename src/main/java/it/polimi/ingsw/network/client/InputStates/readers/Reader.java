package it.polimi.ingsw.network.client.InputStates.readers;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public abstract class Reader {
    String input;
    boolean isReading;

    public abstract void run();

    /**
     * Waits for the user to input a string and updates the input variable accordingly
     */
    void getInput() {
        try {
            input = readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return a boolean indicating if the user is using a command
     */
    public boolean isReading() {
        return isReading;
    }

    /**
     * Waits for the user to input a command and calls the corresponding executor
     */
    void chooseCommand() {
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
