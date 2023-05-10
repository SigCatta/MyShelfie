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
            System.out.println(
                    "You can use the following commands:\n" +
                            " - pickup_tiles\n" +
                            " - insert_tiles\n" +
                            " - chat\n" +
                            " - disconnect\n" +
                            " - common_goals\n" +
                            " - personal_goal\n" +
                            " - shelves\n"
            ); //TODO other commands?

            chooseCommand();
        }
    }
}
