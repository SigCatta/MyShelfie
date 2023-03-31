package it.polimi.ingsw.model.observers;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;


public class FullShelfObserver {
    private Game game;
    private boolean firstShelfFull;

    public FullShelfObserver(Game game) {
        firstShelfFull = false;
        this.game = game;
        for(Player player: game.getPlayers()) {
            player.getShelf().registerObserver(this);
        }
    }
    public void shelfFull() {
        firstShelfFull = true;
        //game.getScoreBoard().setFirstPlayerToFinish(game.getActivePlayer());

        //TODO: call controller to modify the view
        //TODO: control that after the end of the last player turn, the game ends
    }

    public void endTurn() {
        int firstPlayerIndex = game.getPlayers().indexOf(game.getFirstPlayer());
        int lastPlayerIndex = firstPlayerIndex==0 ? game.getPlayers().size()-1
                                                    : firstPlayerIndex-1;

        if(game.getActivePlayer().equals(game.getPlayers().get(lastPlayerIndex))) {
            //TODO: call controller to modify the view
            //TODO: end the game
        }
    }
}
