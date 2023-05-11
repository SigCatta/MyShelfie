package it.polimi.ingsw.View.CLI.InputStates.readers.commandExecutors;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.CLI.Elements.TilesTableView;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.TilesTableRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class InsertTilesCE implements CommandExecutor {

    /**
     * Asks the user for a tile to insert in the shelf and where to insert it,
     * then sends a message to the server to insert the choosen tiles
     */
    @Override
    public void execute() {
        if (!GameRepresentation.getInstance().getActivePlayerNickname().equals(SocketClient.getInstance().getNickname())) {
            System.out.println("ERROR: You are not the active player!");
            return;
        }
        if (GameRepresentation.getInstance().getGameState() != GameState.INSERT_TILES) {
            System.out.println("ERROR: You can't insert tiles at this state!");
            return;
        }

        System.out.println("You can insert the following tiles");
        new TilesTableView().getPrint(new ArrayList<>()).forEach(System.out::println);

        int tile = getTileIndex();
        int column = getColumn();


        SocketClient.getInstance().sendCommand(new InsertTileMTS(tile - 1, column));
    }

    /**
     * Asks the user to choose which tile to insert in his shelf
     * from the ones he has picke up eralier
     *
     * @return the index of the coosen tile in the tiles table
     */
    private int getTileIndex() {
        int tile;
        while (true) {
            System.out.println("Which tile would you like to insert? (1 - 3)");
            try {
                tile = Integer.parseInt(getInput());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number!");
                continue;
            }
            if (tile < 1 || tile > TilesTableRepresentation.getInstance().getTiles().size()) System.out.println("ERROR: invalid number");
            else return tile;
        }
    }

    /**
     * Asks the user to pick a column where to insert the tiles
     *
     * @return the column where to insert the tiles
     */
    private int getColumn() {
        int column;
        while (true) {
            System.out.println("Where would you like to insert the column? (0 - 4)");
            try {
                column = Integer.parseInt(getInput());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number!");
                continue;
            }
            if (column < 0 || column > 4) System.out.println("ERROR: invalid number");
            else return column;
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
