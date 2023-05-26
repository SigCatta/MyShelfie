package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.View.CLI.Elements.GameStopper;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualView.Messages.GameMTC;
import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.client.SocketClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class EndGameTest {

    @Test
    @Disabled
    public void endTest() {
        GameStopper.getInstance();
        SocketClient.getInstance().setNickname("TEST");

        Player p = new Player("TEST");
        Player p2 = new Player("TEST2");

        ItemTile[][] mat = {
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE)},
        };
        p.getShelf().setShelfGrid(mat);
        p2.getShelf().setShelfGrid(mat);

        p.updateScore(2);
        PlayersRepresentation.getInstance().updatePlayer(new PlayerMTC(p));
        PlayersRepresentation.getInstance().updatePlayer(new PlayerMTC(p2));

        Game g = new Game(2);
        g.setGameState(GameState.END);
        GameRepresentation.getInstance().setGame(new GameMTC(g));
    }
}
