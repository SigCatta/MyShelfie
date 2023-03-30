package it.polimi.ingsw.model.board.TilesGetter;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class TilesGetter {
    private Board board;
    private final PickUpValidator PICK_UP_VALIDATOR;
    private Player activePlayer;

    public TilesGetter(Game game){
        PICK_UP_VALIDATOR = new PickUpValidator(game);

        board = game.getBoard();
        activePlayer = game.getActivePlayer();
    }

    public boolean pickUpTiles(ArrayList<Point> chosenPositions){
        if(!PICK_UP_VALIDATOR.isValid(chosenPositions)) return false;
        //TODO send tiles to the shelf buffer and remove them from the board
        return true;
    }

}
