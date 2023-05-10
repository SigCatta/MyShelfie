package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonCardDealer {

    /**
     * initialized only the first time in the server
     */
    public static List<CommonGoalStrategy> possibleCommonGoals;

    /**
     * @param number: the number of CommonGoals needed
     * @return list of random common goal rules (no duplicates).
     */
    public static List<CommonGoalStrategy> pickCardStrategies(int number) {
        if (possibleCommonGoals == null) {
            initCommonGoals();
        }
        List<CommonGoalStrategy> gameCGStrategies = new ArrayList<>();
        Set<Integer> randomChosen = new HashSet<>(); //to avoid duplicates

        for (int i = 0; i < number; i++) {
            int random = (int) (Math.random() * possibleCommonGoals.size());

            if (randomChosen.contains(random)) {
                i--;
                continue;
            }

            gameCGStrategies.add(possibleCommonGoals.get(random));
            randomChosen.add(random);

            if (randomChosen.size() == possibleCommonGoals.size()) break;
        }

        return gameCGStrategies;
    }

    /**
     * @param number: the number of commonGoal cards requested
     * @return a list of {@param number} CommonGoal .
     */
    public static List<CommonGoalCard> pickCommonGoalCards(int number) {
        List<CommonGoalCard> commonGoalCards = new ArrayList<>();
        List<CommonGoalStrategy> gameCGStrategies = pickCardStrategies(number);

        for (int i = 0; i < number; i++) {
            CommonGoalCard cg = new CommonGoalCard(gameCGStrategies.get(i));
            commonGoalCards.add(cg);
        }
        return commonGoalCards;
    }

    private static void initCommonGoals() {
        possibleCommonGoals = new ArrayList<>();
        possibleCommonGoals.add(new EightTilesSameColorCGS());
        possibleCommonGoals.add(new FiveTilesDiagonalCGS());
        possibleCommonGoals.add(new FourAnglesCGS());
        possibleCommonGoals.add(new FourGroupsOfFourCGS());
        possibleCommonGoals.add(new FourRowsOfFiveCGS());
        possibleCommonGoals.add(new ScaleCGS());
        possibleCommonGoals.add(new SixGroupsOfTwoCGS());
        possibleCommonGoals.add(new ThreeMaxThreeDiffCGS());
        possibleCommonGoals.add(new TwoColSixDiffCGS());
        possibleCommonGoals.add(new TwoRowsWithFiveDiffCGS());
        possibleCommonGoals.add(new TwoSquaresCGS());
        possibleCommonGoals.add(new XShapedCGS());
    }

}

