package it.polimi.ingsw.model.cards.personalGoals;

import it.polimi.ingsw.JSONReader.PersonalGoalReader;
import it.polimi.ingsw.Enum.Color;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalGoalReaderTest {
    /**
     * This class Tests {@link PersonalGoalReader}
     */

    private static PersonalGoalReader reader;


    @BeforeAll
    public static void init() {
        reader = new PersonalGoalReader();
    }

    @Test
    public void testPointStack() throws IOException, ParseException {
        Stack<Integer> expected = new Stack<>();

        expected.push(12);
        expected.push(9);
        expected.push(6);
        expected.push(4);
        expected.push(2);
        expected.push(1);
        Stack<Integer> returned = reader.getPointStack();
        assertEquals(returned, expected);
    }

    @Test
    public void testMapReading() throws IOException, ParseException {
        String file = "1.json";
        HashMap<Color, Point> expected = new HashMap<>();

        expected.put(Color.GREEN, new Point(5, 2));
        expected.put(Color.YELLOW, new Point(2, 4));
        expected.put(Color.BLUE, new Point(3, 1));
        expected.put(Color.LIGHTBLUE, new Point(3, 6));
        expected.put(Color.PINK, new Point(1, 1));
        expected.put(Color.WHITE, new Point(4, 3));

        HashMap<Color, Point> tested = reader.getPersonalGoalsData(file);
        assertEquals(tested, expected);
    }
}