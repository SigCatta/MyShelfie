package it.polimi.ingsw.model.player;

import static org.junit.Assert.*;


import org.junit.Test;

public class PlayerTest {
    private Player player;

    @Test
    public void testGetScore() {
        player = new Player();
        assertEquals(0, player.getScore());
        player.updateScore(10);
        assertEquals(10, player.getScore());
        player.updateScore(-5);
        assertEquals(5, player.getScore());
    }


    @Test
    public void testUpdateScore() {
        player = new Player();
        player.updateScore(5);
        assertEquals(5, player.getScore());
        player.updateScore(-3);
        assertEquals(2, player.getScore());
    }

}
