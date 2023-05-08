package it.polimi.ingsw.network.client.InputStates.readers;

public class PlayingReader extends Reader implements Runnable {
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
