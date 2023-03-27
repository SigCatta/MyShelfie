package it.polimi.ingsw.JSONReader;

import org.json.simple.parser.JSONParser;

public abstract class JSONReader {

    protected final JSONParser jsonParser;

    public JSONReader() {
        this.jsonParser = new JSONParser();
    }
}
