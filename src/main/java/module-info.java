open module proj.ingsw.The.Compiler.Coalition {
    requires java.logging;
    requires json.simple;
    requires java.desktop;
    exports it.polimi.ingsw;

    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    //opens it.polimi.ingsw.View.GUI to javafx.fxml;
    exports it.polimi.ingsw.View.GUI;
    exports it.polimi.ingsw.View.GUI.SceneController;
    exports it.polimi.ingsw.View.GUI.GuiObservers;
    //opens it.polimi.ingsw.View.GUI.SceneController to javafx.fxml;
}