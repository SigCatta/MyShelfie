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
        messageInChat[row][col].setVisible(false);
    }
    public static void setMessage(String message, int row, int col) {
        messageInChat[row][col].setText(message);
        if(message.length()>0)
            messageInChat[row][col].setVisible(true);
    }

    public static void clear() {
        for (int i = 0; i < messageInChat.length; i++) {
            for (int j = 0; j < messageInChat[i].length; j++) {
                if(messageInChat[i][j]!=null) {
                    messageInChat[i][j].setText("");
                    messageInChat[i][j].setVisible(false);
                }
            }
        }
    }
}
