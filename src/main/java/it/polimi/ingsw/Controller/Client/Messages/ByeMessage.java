package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

/**
 * this message removes a player permanently from the game
 */
public class ByeMessage extends MessageToServer{

    @Override
    public void update(Game game) {
        ServerController.getInstance().bye(this, game);
    }

}
