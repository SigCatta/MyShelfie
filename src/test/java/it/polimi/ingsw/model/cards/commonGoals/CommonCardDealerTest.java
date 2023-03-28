package it.polimi.ingsw.model.cards.commonGoals;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class CommonCardDealerTest {
    @Test
    public void getCardsTest() {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.getCardStrategies();
        assertNotNull(commonGoalStrategyList);
        assertEquals(12, commonGoalStrategyList.size());
    }

    @Test
    public void pickCardsTest() {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.pickCardStrategies(2);
        assertNotNull(commonGoalStrategyList);
        assertEquals(2, commonGoalStrategyList.size());
    }

}
