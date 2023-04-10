package it.polimi.ingsw.Controller.Executer;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CanIPlayExecutor implements Executor {

    Game game;

    CanIPlayExecutor(Game game) {
        this.game = game;
    }

    @Override
    public void execute(HashMap<String, String> data) {
        ArrayList<Player> players = game.getPlayers();
        String nickname = data.get("NICKNAME");

        if (game.getMAX_PLAYER_NUMBER() <= players.size()) {
            // TODO send message "the game is full"
            return;
        }

        for (Player p : players) {
            if (p.getNickname().equals(nickname)) {
                //TODO send message "already connected"
                return;
            }
        }

        game.getPlayers().add(new Player()); // TODO set the players nickname
        //TODO send message "connections successfull"
    }
}
