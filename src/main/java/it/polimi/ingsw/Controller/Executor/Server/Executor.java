package it.polimi.ingsw.Controller.Executor.Server;

import org.json.simple.JSONObject;
import java.util.HashMap;

public interface Executor {
    //protected GamesManager = GameManager.getInstance();
    void execute(HashMap<String, String> data);
}
