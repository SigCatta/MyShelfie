open module proj.ingsw.The.Compiler.Coalition {
    requires java.logging;
    requires json.simple;
    requires java.desktop;
    exports it.polimi.ingsw;

    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    exports it.polimi.ingsw.View.GUI;
    exports it.polimi.ingsw.View.GUI.SceneController;
    exports it.polimi.ingsw.model.tiles;
    exports it.polimi.ingsw.Enum;
    exports it.polimi.ingsw.VirtualView.Messages;
    exports it.polimi.ingsw.model.cards.commonGoals;
    exports it.polimi.ingsw.model.player;
    exports it.polimi.ingsw.VirtualView.ModelObservers;
    exports it.polimi.ingsw.model.board;
    exports it.polimi.ingsw.VirtualView;
    exports it.polimi.ingsw.model;
    exports it.polimi.ingsw.network.server;

}