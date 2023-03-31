package it.polimi.ingsw.model.observers;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;


public class FullShelfObserver {
    private Game game;

    public FullShelfObserver(Game game) {
        this.game = game;
        for(Player player: game.getPlayers()) {
            player.getShelf().registerObserver(this);
        }
    }
    public void shelfFull() {
        game.getScoreBoard().setFirstPlayerToFinish(game.getActivePlayer());

        //TODO: call controller to modify the view
        //TODO: control that after the end of the last player turn, the game ends
    }


}
