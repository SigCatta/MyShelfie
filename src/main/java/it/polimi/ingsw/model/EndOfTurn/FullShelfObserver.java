package it.polimi.ingsw.model.EndOfTurn;

import it.polimi.ingsw.model.EndOfTurn.ScoreCalculation.ScoreBoard;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;


public class FullShelfObserver  implements EndOfTurnObserver{
    private Game game;
    private boolean firstShelfFull;
    private ScoreBoard scoreBoard;

    public FullShelfObserver(Game game) {
        scoreBoard = new ScoreBoard(game);
        firstShelfFull = false;
        this.game = game;
        for(Player player: game.getPlayers()) {
            player.getShelf().registerObserver(this);
        }
        game.getTurnHandler().attachEndOfTurn(this);
    }
    public void shelfFull() {
        if(!firstShelfFull){
            firstShelfFull = true;
            //TODO: call method in scoreBoard to add point to the active player

            //TODO: call controller to modify the view
        }
    }

    @Override
    public void update() {
        if(firstShelfFull) {
            int firstPlayerIndex = game.getPlayers().indexOf(game.getFirstPlayer());
            int lastPlayerIndex = firstPlayerIndex==0 ? game.getPlayers().size()-1
                    : firstPlayerIndex-1;

            if(game.getActivePlayer().equals(game.getPlayers().get(lastPlayerIndex))) {
                //TODO: call controller to modify the view
                //TODO: end the game
            }
        }
    }
}
