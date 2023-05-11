package it.polimi.ingsw.View.CLI.InputStates.readers;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;

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
        } catch (NullPointerException ignored) {
        }catch (RejectedExecutionException e){
            input = null;
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
        if (input == null) return;

        isReading = true;
        input = input.toLowerCase();
        CommandExecutorFactory.getCommand(input).execute();

        isReading = false;
        synchronized (this) {
            notifyAll();
        }
    }

}
