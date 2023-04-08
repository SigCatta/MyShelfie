package it.polimi.ingsw.Controller.Executor.Server;

import org.json.simple.JSONObject;

import java.util.HashMap;

public interface Executor {
    void execute(HashMap<String, String> data);
}
