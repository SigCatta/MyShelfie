package it.polimi.ingsw.View.CLI.InputStates.reader.commandExecutors;

import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.CLI.InputStates.reader.CommandExecutorFactory;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

import java.awt.*;
import java.util.ArrayList;

public class PickupTilesCE implements CommandExecutor {

    /**
     * Makes the player select the location of the cards to pickup
     */
    @Override
    public void execute() {
        if (!GameRepresentation.getInstance().getActivePlayerNickname().equals(SocketClient.getInstance().getNickname())) {
            System.out.println("ERROR: You are not the active player!");
            return;
        }
        if (GameRepresentation.getInstance().getGameState() != GameState.PICK_UP_TILES) {
            System.out.println("ERROR: You can't pickup tiles at this state!");
            return;
        }

        int pickedUpTiles = 0;
        ArrayList<Point> tiles = new ArrayList<>();

        while (true) {
            int column = getColumn();
            if (column == -1) {
                CommandExecutorFactory.getCommand("refresh").execute();
                return;
            }

            int row = getRow();
            if (row == -1) continue;

            tiles.add(new Point(row, column));
            pickedUpTiles++;
            System.out.println("Tile at loaction " + column + ", " + row + " set for pickup");

            if (pickedUpTiles >= 3) break;

            int stop = stopPickup();
            if (stop == 1) break;
            else if (stop == -1) return;
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
        while (true) {
            System.out.println("Which column would you like to select?");
            String input = Reader.getInput();
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
            System.out.println("Which row would you like to select?");
            input = Reader.getInput();
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
     * Asks the user whether to keep pickup up tiles or not
     *
     * @return an integer of values:<br>
     * 1 if the user wants to stop picking up tiles<br>
     * 0 if the user wants to pick up more tiles<br>
     * -1 if the user wants to go back<br>
     */
    private int stopPickup() {
        while (true) {
            System.out.println("Do you want to pickup more tiles? (y/n)");
            String input = Reader.getInput();
            if (input.equalsIgnoreCase("n")) return 1;
            else if (input.equalsIgnoreCase("y")) return 0;
            if (input.equals(".")) return -1;
        }
    }
}
