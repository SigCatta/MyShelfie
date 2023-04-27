package it.polimi.ingsw.JSONReader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class GameStateDataReader implements JSONFileReader{

    public static Set<String> getSetFromJSON(String fileName) {

        String PATH = "src/data/GameState/";

        JSONParser parser = new JSONParser();
        Set<String> mySet = new HashSet<>();

        try {
            // Read JSON file
            Object obj = parser.parse(new FileReader(PATH + fileName));
            JSONArray jsonArray = (JSONArray) obj;

            // Convert JSON array to Set
            for (Object o : jsonArray) {
                String command = (String) o;
                mySet.add(command);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mySet;
    }
}