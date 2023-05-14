package it.polimi.ingsw.View.CLI.InputStates.reader;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class Reader implements Runnable, VirtualModelObserver {
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
        input = getInput();

        input = input.toLowerCase();
        isReading = true;
        CommandExecutorFactory.getCommand(input).execute();

        isReading = false;
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * Waits for the user to input a string and updates the input variable accordingly
     */
    public static String getInput() {
        try {
            return readLine().trim();
        } catch (ExecutionException e) {
            return getInput();
        } catch (NullPointerException e) {
            return ".";
        }
    }

    @Override
    public void update() {
        while (isReading()) { // if the user is using a command the view does not update
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        EchoMTC message = EchosRepresentation.getInstance().getMessage();
        if (message != null) {
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
                Printer.clearConsole();
                Printer.getInstance().update();
            }
        }
    }
}
