package it.polimi.ingsw.Controller.Server.Executor;


import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.Controller.Client.PickUpTilesMTS;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.ChosenTilesTable.PickUpValidator;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.server.SocketClientHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class is responsible for executing the {@link PickUpTilesMTS} command received from the client.
 * It checks the validity of the positions and removes the corresponding tiles from the board,
 * placing them in the chosen tiles table.
 */
public class PickupTilesExecutor implements Executor {

    /**
     * checks if the received message contains valid positions and
     * removes the tiles from the board sending them to the
     * chosen tiles table
     *
     * @param message {@link PickUpTilesMTS} containing which tiles to pick up
     */
    public static void execute(MessageToServer message) {

        Game game = message.getGame();
        SocketClientHandler client = message.getSocketClientHandler();
        PickUpTilesMTS pickUpTilesMessage = (PickUpTilesMTS) message;

        if (!(game.getGameState() == GameState.PICK_UP_TILES)) {
            client.sendCommand(new EchoMTC(EchoID.WRONGSTATE, true));
            return;
        }
        if (!pickUpTilesMessage.getNickname().equals(game.getActivePlayer().getNickname())) {
            client.sendCommand(new EchoMTC(EchoID.NOTACTIVE, true));
            return;
        }
        if (!PickUpValidator.isValid(game, pickUpTilesMessage.getTilesPosition())) {
            client.sendCommand(new EchoMTC(EchoID.BADPICKUP, true));
            return;
        }

        ArrayList<Point> tilesPosition = pickUpTilesMessage.getTilesPosition();
        if (tilesPosition == null) return;

        ArrayList<Point> positions = pickUpTilesMessage.getTilesPosition();

        ArrayList<ItemTile> tiles = game.getBoard().removeItemTiles(positions);
        game.getChosenTilesTable().addTiles(tiles);

        game.setGameState(GameState.INSERT_TILES);
    }
}