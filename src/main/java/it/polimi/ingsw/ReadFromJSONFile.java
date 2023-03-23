package it.polimi.ingsw;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromJSONFile {
    JSONParser jsonParser;

    public ReadFromJSONFile() {
        this.jsonParser = new JSONParser();
    }

    public void readJSON(String fileName) throws IOException, ParseException {
        FileReader fileReader = new FileReader(fileName);
        Object obj = jsonParser.parse(fileReader);

    }

}
