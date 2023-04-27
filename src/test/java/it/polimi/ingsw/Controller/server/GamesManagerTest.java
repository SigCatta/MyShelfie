package it.polimi.ingsw.Controller.server;

import it.polimi.ingsw.Controller.Server.GamesManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamesManagerTest {
    @Test
    public void isSingleton(){
        GamesManager gamesManager1 = GamesManager.getInstance();
        gamesManager1.newGame(99);
        GamesManager gamesManager2 = GamesManager.getInstance();

        Assertions.assertEquals(1, gamesManager2.getNumberOfGames());
    }

}
