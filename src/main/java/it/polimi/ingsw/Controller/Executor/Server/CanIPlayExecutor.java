package it.polimi.ingsw.Controller.Executor.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CanIPlayExecutor implements Executor {

    private GamesManager gamesManager;


    CanIPlayExecutor() {
        this.gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        Game game = gamesManager.getGame(Integer.parseInt(data.get("GAME_ID")));

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

        game.getPlayers().add(new Player(nickname)); // TODO set the players nickname
        //TODO send message "connections successful"
    }
}
