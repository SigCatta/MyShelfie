package it.polimi.ingsw.GameState;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class GameStateTest {

    @Test
    public void stateSuccessionTest() {
        Game game = new Game(999);
        game.setVirtualView(new VirtualView(game));
        assertSame(game.getGameState(), GameState.PREGAME);

        game.addPlayer(new Player("a"));
        game.addPlayer(new Player("b"));
        game.addPlayer(new Player("c"));
        game.setActivePlayer(game.getPlayers().get(0));

//        game.start();
//        assertTrue(game.getGameState() instanceof PickUpTilesState);
//
//        ArrayList<Point> tilesPosition = new ArrayList<>();
//        tilesPosition.add(new Point(1, 3));
//        tilesPosition.add(new Point(1, 4));
//
//        assertTrue(game.get.pickUpTiles(tilesPosition));
//
//        assertTrue(game.getGameState() instanceof InsertTilesState);
//
//        assertTrue(game.getTilesGetter().sendTilesToShelf(0, 2));
//        assertFalse(game.getTilesGetter().sendTilesToShelf(1, 3));
//        assertTrue(game.getTilesGetter().sendTilesToShelf(0, 2));
//
//        assertTrue(game.getGameState() instanceof PickUpTilesState);
//
//        assertEquals("b", game.getActivePlayer().getNickname()); //player b
//        Shelf bShelf = game.getActivePlayer().getShelf();
//
//        for(int i = 0; i < bShelf.getROWS(); i++){
//            for(int j = 0; j < bShelf.getCOLUMNS(); j++){
//                bShelf.insertTile(new ItemTile(Color.BLUE), j);
//            }
//        }
//
//        game.getTurnHandler().changeTurn();
//        assertTrue(game.getTurnHandler().isLastTurn());
//
    }

}
