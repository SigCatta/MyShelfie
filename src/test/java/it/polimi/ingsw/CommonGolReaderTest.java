package it.polimi.ingsw;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CommonGolReaderTest {
    @Test
    public void test() throws IOException, ParseException {
        CommonGoalReader cgr = new CommonGoalReader();
        cgr.getDrawing("Scale").forEach(System.out::println);
    }
}
