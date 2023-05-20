package it.polimi.ingsw.View.GUI.SceneController.Utility;


import javafx.scene.control.TextField;

public class ChatMemory {
    private static final TextField[][] messageInChat = new TextField[13][2];

    public static TextField getMessage(int row, int col) {
        return messageInChat[row][col];
    }

    public static void setMessage(TextField message, int row, int col) {
        messageInChat[row][col] = message;
        messageInChat[row][col].setVisible(false);
    }

    public static void setMessage(String message, int row, int col) {
        messageInChat[row][col].setText(message);
        if (message.length() > 0)
            messageInChat[row][col].setVisible(true);
    }

    public static void clear() {
        for (TextField[] textFields : messageInChat) {
            for (TextField textField : textFields) {
                if (textField != null) {
                    textField.setText("");
                    textField.setVisible(false);
                }
            }
        }
    }
}
