package it.polimi.ingsw.View.CLI.InputStates.readers;

public class PlayingReader extends Reader implements Runnable {

    /**
     * Prints te commands an active player can execute,
     * calls the inherited chooseCommand method to wait for the command and execute it
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            chooseCommand();
        }
    }
}
