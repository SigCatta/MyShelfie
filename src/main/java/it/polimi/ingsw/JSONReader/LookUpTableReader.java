package it.polimi.ingsw.JSONReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads look up tables data from json files
 */
public class LookUpTableReader implements JSONFileReader {

    protected final JSONParser jsonParser;

    public LookUpTableReader() {
        this.jsonParser = new JSONParser();
    }

    /**
     * Reads the resources/data/boards folder to get which spots on the board a playable, reads the files based on
     * how many players are playing, allowing more tile for bigger games
     *
     * @param numOfPlayers indicates how many players will be playing
     * @return a boolean matrix contain true in the position that has to be filled
     */
    public boolean[][] getLookUpTable(int numOfPlayers) {

        boolean[][] lookUpTable = null;

        if (numOfPlayers < 2) numOfPlayers = 2;
        else if (numOfPlayers > 4) numOfPlayers = 4;


        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/boards/" + numOfPlayers + ".json");
        assert inputStream != null;
        InputStreamReader reader = new InputStreamReader(inputStream);
        try {
            JSONObject JSONObj = (JSONObject) jsonParser.parse(reader);
            JSONArray rows = (JSONArray) JSONObj.get("lookUpTable");

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
