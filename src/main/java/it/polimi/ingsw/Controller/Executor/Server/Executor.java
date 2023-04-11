package it.polimi.ingsw.Controller.Executor.Server;


import java.util.HashMap;

public interface Executor {
    //protected GamesManager = GameManager.getInstance();
    void execute(HashMap<String, String> data);
}
