package it.polimi.ingsw.Controller.Server.ServerController;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.model.Game;

public interface ServerVisitor {
    void visit(MessageToServer message, Game game);
}
