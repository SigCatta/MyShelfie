package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.network.client.SocketClient;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class PickupTilesCE implements CommandExecutor {

    /**
     * Makes the player select the location of the cards to pickup
     */
    @Override
    public void execute() {
        int pickedUpTiles = 0;
        ArrayList<Point> tiles = new ArrayList<>();

        while (true) {
            int column = getColumn();
            if (column == -1) return;

            int row = getRow();
            if (row == -1) continue;

            tiles.add(new Point(row, column));

            if (++pickedUpTiles < 3) {
                System.out.println("Do you want to pickup more tiles? (y/n)");
                if (getInput().equalsIgnoreCase("n")) break;
            } else break;
        }

        SocketClient.getInstance().sendCommand(new PickUpTilesMTS(tiles));
    }

    /**
     * Asks to select a column and returns it if valid,
     * returns -1 if the user inputs "." to <i>go back</i>
     *
     * @return the selected column
     */
    private int getColumn() {
        String input;
        while (true) {
            System.out.println("Select which column you would like to select: ");
            input = getInput();
            if (input.equals(".")) return -1;
            if (isInputValid(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input! Insert a column number between 0 an 8");
            }
        }
    }

    /**
     * Asks to select a row and returns it if valid,
     * returns -1 if the user inputs "." to <i>go back</i>
     *
     * @return the selected row
     */
    private int getRow() {
        String input;
        while (true) {
            System.out.println("Select which row you would like to select: ");
            input = getInput();
            if (input.equalsIgnoreCase(".")) return -1;
            if (isInputValid(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input! Insert a row number between 0 an 8");
            }
        }
    }

    /**
     * Checks if a given String is between 0 and 8 (board dimension)
     *
     * @param input the String to valide
     * @return a boolean indicating if the input String is valid
     */
    private boolean isInputValid(String input) {
        try {
            return Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 8;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Reads user input
     *
     * @return user input
     */
    private String getInput() {
        try {
            return readLine().trim();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
