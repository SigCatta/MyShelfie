package it.polimi.ingsw.View.GUI.SceneController.Utility;


import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ChatMemory {
    private static TextField[][] messageInChat = new TextField[13][2];

    public static TextField getMessage(int row, int col) {
        return messageInChat[row][col];
    }

    public static void setMessage(TextField message, int row, int col) {
        messageInChat[row][col] = message;
    }
    public static void setMessage(String message, int row, int col) {
        messageInChat[row][col].setText(message);
    }

    public static void reset() {
        messageInChat = new TextField[13][2];
    }
}
