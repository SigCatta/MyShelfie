package it.polimi.ingsw.model.EndOfTurn.ScoreCalculation;

import exceptions.NullItemTileException;
import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.EndOfTurn.EndOfTurnObserver;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.cards.commonGoals.CommonCardDealer;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ScoreBoard implements EndOfTurnObserver {

    private final ArrayList<Player> players;
    private final List<CommonGoalCard> commonGoalCards;
    private Player activePlayer;
    private boolean isFirstPointAssigned;

    public ScoreBoard(Game game) throws TooManyCardsRequestedException {
        this.commonGoalCards = CommonCardDealer.pickCommonGoalCards(2);
        this.players = game.getPlayers();
        this.activePlayer = game.getActivePlayer();
        this.isFirstPointAssigned = false;
    }


    private void scoreCommonGoalCards(Player player) { // at the end of each turn
        for (CommonGoalCard commonGoal : commonGoalCards) {
            int points = commonGoal.calculateScore(player);
            player.updateScore(points);
        }
    }

    @Override
    public void update() throws NullItemTileException { // FULL SHELF
        scoreCommonGoalCards(activePlayer);

        if (activePlayer.getShelf().isFull()) {
            if (!isFirstPointAssigned) {
                scoreFirstCompletedShelf(activePlayer);
                isFirstPointAssigned = true;
            }
        }

        if (isFirstPointAssigned && activePlayer == players.get(players.size() - 1)) {
            scorePersonalGoals();
            scoreAdjacency();
        }
    }


    private void scoreFirstCompletedShelf(Player firstPlayer) { // only the first time a player fills the shelf
        if (!isFirstPointAssigned) {
            isFirstPointAssigned = true;
            firstPlayer.updateScore(1);
        }
    }

    private void scorePersonalGoals() throws NullItemTileException { // at the end of the game
        for (Player player : players) {
            int points = player.getPersonalGoal().calculateScore();
            player.updateScore(points);
        }
    }

    private void scoreAdjacency() { // at the end of the game
        for (Player player : players) {
            int points = AdjacencyScoreCalculation.calculateScore(player);
            player.updateScore(points);
        }
    }

    public Player getWinner() throws NoSuchElementException {
        return players.stream().max((p1, p2) -> p1.getScore() > p2.getScore() ? 1 : -1).orElse(null);
    }

}
