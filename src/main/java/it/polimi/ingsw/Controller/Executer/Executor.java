package it.polimi.ingsw.Controller.Executer;

import org.json.simple.JSONObject;

import java.util.HashMap;

public interface Executor {
    void execute(HashMap<String, String> data);
}
