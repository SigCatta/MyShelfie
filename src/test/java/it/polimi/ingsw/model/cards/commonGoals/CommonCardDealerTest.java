package it.polimi.ingsw.model.cards.commonGoals;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class CommonCardDealerTest {
    @Test
    public void getCardsTest() {
        List<CommonGoal> commonGoalList = CommonCardDealer.getCards();
        assertNotNull(commonGoalList);
        assertEquals(12, commonGoalList.size());
    }

    @Test
    public void pickCardsTest() {
        List<CommonGoal> commonGoalList = CommonCardDealer.pickCards(2);
        assertNotNull(commonGoalList);
        assertEquals(2, commonGoalList.size());
    }

}
