package it.polimi.ingsw.network.client.InputStates.readers;

import it.polimi.ingsw.View.CLI.ShelfView;

import java.util.ArrayList;

public class WaitingReader extends Reader implements Runnable {
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            System.out.println(
                    "You can use the following commands:\n" +
                            " - chat\n" +
                            " - disconnect\n" +
                            " - common_goals\n" +
                            " - personal_goal\n" +
                            " - shelves\n"
            ); //TODO other commands?
            getInput();

            isReading = true;
            input = input.toLowerCase();
            executeCommand();

            isReading = false;
            synchronized (this){
                notifyAll();
            }
        }
    }

    @Override
    public void executeCommand() {
        switch (input) {
            case ".":
                return;
            case "chat":
                System.out.println("Insert message: ");
                getInput();
                //TODO send the message to the server
                return;
            case "disconnect":
                System.out.println("Are you sure you want to leave? (y/n)");
                getInput();
                if (input.equalsIgnoreCase("y")) {
                    System.out.println("Leaving...");
                    //TODO send message to the server
                }
                return;
            case "common_goals":
                //TODO print all common goals with their respective description
                return;
            case "personal_goal":
                //TODO print the personal goal with an explanation on how to get points etc.
                return;
            case "shelves":
                System.out.println("Whose shelf do you want to see?");
                getInput();
                ArrayList<String> output = new ShelfView().getOtherShelvsPrint(input);
                output.forEach(System.out::println);
                return;
        }
        System.out.println("ERROR: invalid command!");
    }

}
