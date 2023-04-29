package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;

public interface Executor {
    void execute(MessageToServer data);
}
