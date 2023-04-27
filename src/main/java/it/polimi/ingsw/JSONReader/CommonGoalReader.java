package it.polimi.ingsw.JSONReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommonGoalReader implements JSONFileReader {
    protected final JSONParser jsonParser;
    private final String PATH;

    public CommonGoalReader() {
        this.jsonParser = new JSONParser();
        PATH = "src/data/common_cards/";
    }

    /**
     * Reads the src/data/common_cards folder to get the cli representation of any commonGoal card
     *
     * @param cardName the name of the card to be read
     * @return a String arrayList containing the drawing
     */
    public ArrayList<String> getDrawing(String cardName) {
        ArrayList<String> drawing = new ArrayList<>();
        try {
            FileReader reader = new FileReader(PATH + cardName + ".json");
            JSONObject data = (JSONObject) jsonParser.parse(reader);
            JSONArray rows = (JSONArray) data.get("DRAWING");

            for (Object line : rows) {
                drawing.add((String) line);
            }
            return drawing;
        } catch (IOException | ParseException e) {
            return null;
        }
    }
}
