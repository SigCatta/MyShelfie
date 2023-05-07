package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.CLI.BoardView;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardViewTest {
    @BeforeEach
    public void setup() {

    }

    @Test
    public void boardViewTest() {
        BoardRepresentation.getInstance();
        ArrayList<String> board = new BoardView().getPrint(new ArrayList<>());
        new BoardView().getPrint(board);
    }
}
