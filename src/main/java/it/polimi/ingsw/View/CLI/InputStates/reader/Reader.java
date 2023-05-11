package it.polimi.ingsw.View.CLI.InputStates.reader;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class Reader implements Runnable {
    String input;
    boolean isReading;

    /**
     * waits for user imputs and use {@link CommandExecutorFactory} to run it
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            chooseCommand();
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
    private void chooseCommand() {
        getInput();
        if (input == null) return;

        isReading = true;
        input = input.toLowerCase();
        CommandExecutorFactory.getCommand(input).execute();

        isReading = false;
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * Waits for the user to input a string and updates the input variable accordingly
     */
    private void getInput() {
        try {
            input = readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ignored) {
        }
    }

}
