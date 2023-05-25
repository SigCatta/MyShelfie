package it.polimi.ingsw.VirtualModel;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualView.Messages.BoardMTC;
import it.polimi.ingsw.model.tiles.ItemTile;

public class BoardRepresentation extends VirtualModelSubject {
    private ItemTile[][] board;
    private static BoardRepresentation instance;

    private BoardRepresentation() {
        super();
    }

    public static BoardRepresentation getInstance() {
        if (instance == null) instance = new BoardRepresentation();
        return instance;
    }

    public void setBoard(BoardMTC board) {
        this.board = board.getColorBoard();
        notifyObservers();
    }

    public ItemTile[][] getBoard() {
        return board;
    }

    public Color[][] getBoardColors() {
        Color[][] colorBoard = new Color[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                colorBoard[i][j] = board[i][j] == null ? null : board[i][j].getColor();
            }
        }
        return colorBoard;
    }
}