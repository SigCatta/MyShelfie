package it.polimi.ingsw.Controller.Client.Mappers;

import java.util.HashMap;
import java.util.Stack;

public interface Mappable {
    public HashMap<String, String> map (Stack<String> strings);
}
