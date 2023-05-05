package it.polimi.ingsw.View.VirtualView.ModelObservers;

import it.polimi.ingsw.View.VirtualView.Messages.CommonGoalMessage;
import it.polimi.ingsw.View.VirtualView.VirtualView;
import it.polimi.ingsw.model.Game;

public class CommonGoalsView implements VirtualViewObserver {

    private final Game GAME;
    private final VirtualView VIRTUAL_VIEW;

    public CommonGoalsView(Game game, VirtualView virtualView) {
        this.GAME = game;
        this.VIRTUAL_VIEW = virtualView;
        game.registerObserver(this);
        game.getCommonGoalContainer().registerObserver(this);
        game.getCommonGoalContainer().notifyObservers();
    }

    @Override
    public void update() {
        System.out.println(GAME.getCommonGoalContainer());
        VIRTUAL_VIEW.send(new CommonGoalMessage(GAME.getCommonGoalContainer()));
    }
}
