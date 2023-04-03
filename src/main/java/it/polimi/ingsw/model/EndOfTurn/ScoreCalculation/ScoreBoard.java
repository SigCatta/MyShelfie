package it.polimi.ingsw.model.EndOfTurn.ScoreCalculation;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.EndOfTurn.EndOfTurnObserver;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.cards.commonGoals.CommonCardDealer;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The purpose of this class is to calculate the scores and assign points to the players whenever they complete
 * either a personal or common goal, fill their shelf before the others or place tiles of the same colors
 * adjacently in the shelf, it is called at the end of each turn to check if a player can earn any points
 */
public class ScoreBoard implements EndOfTurnObserver {

    private final ArrayList<Player> players;
    private final List<CommonGoalCard> commonGoalCards;
    private final List<List<Player>> completedCommonGoal;
    private Player activePlayer;
    private boolean isFirstPointAssigned;

    /**
     * creates a new scoreboard and assigns it a game
     */
    public ScoreBoard(Game game) throws TooManyCardsRequestedException {
        this.commonGoalCards = CommonCardDealer.pickCommonGoalCards(2);
        this.players = game.getPlayers();
        this.isFirstPointAssigned = false;
        this.completedCommonGoal =  new ArrayList<>();
        for (CommonGoalCard commonGoal : commonGoalCards){
            completedCommonGoal.add(new ArrayList<>());
        }
    }

    /**
     * Checks if the player has completed any new common goals and give him points accordingly
     */
    private void scoreCommonGoalCards(Player player) { // at the end of each turn
        for (int i = 0;i <  commonGoalCards.size(); i++) {
            if (!completedCommonGoal.get(i).contains(player)){
                completedCommonGoal.get(i).add(player);
                CommonGoalCard commonGoal = commonGoalCards.get(i);
                int points = commonGoal.calculateScore(player);
                player.updateScore(points);
            }
        }
    }


    /**
     * Called each time a player's turn finishes, it checks if the current player has completed any of the common goals and
     * assigns points accordingly. It also calculates points for personal goals and tile adjacency if the game is finished
     */
    @Override
    public void update() {
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

        //TODO call the end of the game
    }

    /**
     * Checks if the player who just finished their turn is the first to complete the shlef,
     * if that is the case, the player wins an extra point and the endgame procedure is activated
     */
    private void scoreFirstCompletedShelf(Player player) { // only the first time a player fills the shelf
        if (!isFirstPointAssigned) {
            isFirstPointAssigned = true;
            player.updateScore(1);
        }
    }

    /**
     * Assigngs each player of points based on the completion of their personal goal card
     */
    private void scorePersonalGoals() { // at the end of the game
        for (Player player : players) {
            int points = player.getPersonalGoal().calculateScore();
            player.updateScore(points);
        }
    }


    /**
     * Assigngs each player of points based on the adjacency of tiles in their shelf
     */
    private void scoreAdjacency() { // at the end of the game
        for (Player player : players) {
            int points = AdjacencyScoreCalculation.calculateScore(player);
            player.updateScore(points);
        }
    }

    /**
     * Checks which player has the highest score and returns it. If two or more players have the same score,
     * the winner is the one who played last
     *
     * @return the winner of the game
     */
    public Player getWinner() throws NoSuchElementException {
        //noinspection ComparatorMethodParameterNotUsed
        return players.stream().max((p1, p2) -> p1.getScore() > p2.getScore() ? 1 : -1).orElse(null);
    }

    public void setActivePlayer(Player activePlayer){
        this.activePlayer = activePlayer;
    }

}
