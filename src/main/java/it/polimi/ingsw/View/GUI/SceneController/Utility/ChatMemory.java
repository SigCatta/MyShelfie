package it.polimi.ingsw.View.GUI.SceneController.Utility;

import javafx.scene.control.TextField;

/**
 * The ChatMemory class is used to manage the messages in the chat.
 * It provides methods to retrieve, set, and clear chat messages.
 */
public class ChatMemory {
    private static final TextField[][] messageInChat = new TextField[13][2];

    /**
     * Retrieves the TextField containing the message at the specified row and column in the chat.
     *
     * @param row the row index of the message
     * @param col the column index of the message
     * @return the TextField containing the message
     */
    public static TextField getMessage(int row, int col) {
        return messageInChat[row][col];
    }

    /**
     * Sets the TextField containing the message at the specified row and column in the chat.
     *
     * @param message the TextField containing the message
     * @param row     the row index of the message
     * @param col     the column index of the message
     */
    public static void setMessage(TextField message, int row, int col) {
        messageInChat[row][col] = message;
        messageInChat[row][col].setVisible(false);
    }

    /**
     * Sets the message text at the specified row and column in the chat.
     * If the message is not empty, the corresponding TextField will be made visible.
     *
     * @param message the message text
     * @param row     the row index of the message
     * @param col     the column index of the message
     */
    public static void setMessage(String message, int row, int col) {
        messageInChat[row][col].setText(message);
        if (message.length() > 0)
            messageInChat[row][col].setVisible(true);
    }

    /**
     * Clears all the messages in the chat by resetting the text and visibility of the TextFields.
     */
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