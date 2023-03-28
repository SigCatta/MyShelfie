package it.polimi.ingsw.JSONReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class LookUpTableReader implements JSONFileReader{

    protected final JSONParser jsonParser;
    private final String PATH;

    public LookUpTableReader(){
        this.jsonParser = new JSONParser();
        PATH = "src/data/boards/";
    }

    /**
     * Reads the src/data/boards folder to get which spots on the board a playable, reads the files based on
     * how many players are playing, allowing more tile for bigger games
     *
     * @param numOfPlayers indicates how many players will be playing
     * @return a boolean matrix contain true in the position that has to be filled
     */
    public boolean[][] getLookUpTable(int numOfPlayers) {

        boolean[][] lookUpTable = null;

        try (FileReader reader = new FileReader(PATH + numOfPlayers + ".json")) {

            JSONObject data = (JSONObject) jsonParser.parse(reader);
            JSONArray rows = (JSONArray) data.get("lookUpTable");

            int numRows = rows.size();
            int numCols = ((JSONArray) rows.get(0)).size();

            lookUpTable = new boolean[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                JSONArray row = (JSONArray) rows.get(i);

                for (int j = 0; j < numCols; j++) {
                    lookUpTable[i][j] = (Boolean) row.get(j);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return lookUpTable;
    }
}
