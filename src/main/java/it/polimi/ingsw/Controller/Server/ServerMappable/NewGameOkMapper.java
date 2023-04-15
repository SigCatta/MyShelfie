package it.polimi.ingsw.Controller.Server.ServerMappable;

import it.polimi.ingsw.model.Game;

import java.util.HashMap;

public class NewGameOkMapper extends ServerMappable {
    @Override
    public void map(Object o) {
        HashMap<String, String> map = new HashMap<>();
        if (! (o instanceof Game)) return; // should never happen
        Game game = (Game) o;

        map.put("COMMAND", "NEW_GAME_OK");
        map.put("GAMEID", String.valueOf(game.getGameID()));
        map.put("NUM_OF_PLAYERS", String.valueOf(game.getMAX_PLAYER_NUMBER()));
    }
}
