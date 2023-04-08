package it.polimi.ingsw.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;


public class GamesManagerTest {
    @Test
    public void isSingleton(){
        GamesManager a = GamesManager.getInstance();
        int id = a.addGame();
        GamesManager b = GamesManager.getInstance();

        Assertions.assertNotNull(b.getGame(id));
    }

    @Test
    public void uniqueGameId(){
        GamesManager gamesManager = GamesManager.getInstance();
        Set<Integer> idSet = new HashSet<>();

        for(int i = 0; i < 1000; i++){
            int id = gamesManager.addGame();
            Assertions.assertFalse(idSet.contains(id));
            idSet.add(id);
        }
    }
}
