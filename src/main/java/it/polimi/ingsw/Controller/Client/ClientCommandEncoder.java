package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Client.Mappers.Mappable;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;
import java.util.Stack;


public class ClientCommandEncoder {

    private HashMap<String, Mappable> commandMap;
    public ClientCommandEncoder() {
    }

    public HashMap<String, String> parse(String string, Player player) {
        // string cannot be null
        HashMap<String, String> map = new HashMap<>();

        Stack<String> strings = addAll(string.split(" "));
        String command = strings.peek();

        commandMap.get(command).map(strings);

        return map;
    }

    private Stack<String> addAll(String[] s) {
        Stack<String> stack = new Stack<>();
        for (int i = s.length - 1; i >= 0; i--) {
            stack.push(s[i]);
        }
        return stack;
    }
}
