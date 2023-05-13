package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;

import java.util.ArrayList;

public class ChatView extends ViewElement {
    private final int chatWidth = 118;
    private final int chatHeigth = 15; // has to be at least 5

    private static ChatView instance;

    private ChatView(){}
    public static ChatView getInstance(){
        if (instance == null) instance = new ChatView();
        return instance;
    }

    /**
     * Adds the chat box to a given ArrayList
     *
     * @param output the ArrayList where to add the chat box
     * @return the given ArrayList with the chat bo added
     */
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        ArrayList<String> chat = getChatPrint();

        if (output.size() != 0) {
            for (int i = 0; i < chat.size(); i++) {
                output.set(i, output.get(i).concat(chat.get(i)));
            }

            return output;
        } else return chat;
    }

    /**
     * @return an ArrayList containing the chat box, returns an enpty ArrayList
     * if there are no messages to show
     */
    private ArrayList<String> getChatPrint() {
        ArrayList<String> drawing = new ArrayList<>();
        ArrayList<ChatMTC> messages = ChatRepresentation.getInstance().getMessages();
        if (messages.size() == 0) return drawing;
        drawing.add(("          ┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐"));
        drawing.add(("          │                                                   CHAT                                                   │"));
        drawing.add(("          ├──────────────────────────────────────────────────────────────────────────────────────────────────────────┤"));

        for (int i = 0; i < Math.min(chatHeigth - 4, messages.size()); i++) {
            ChatMTC message = messages.get(messages.size() - (i + 1));

            String string = "          │ " +
                    message.getSender() +
                    ": " +
                    message.getChatMessage() +
                    padding(message);

            drawing.add(string);
        }

        for (int i = 0; i < chatHeigth - drawing.size() - 1; i++) {
            drawing.add("          │                                                                                                          │");
        }

        drawing.add(("          └──────────────────────────────────────────────────────────────────────────────────────────────────────────┘"));
        return drawing;
    }

    /**
     * Adds padding to fill a line
     *
     * @param message the message that needs padding
     * @return a string containing the necessary padding
     */
    private String padding(ChatMTC message) {
        int paddingLength = chatWidth
                - "          │ ".length()
                - message.getSender().length()
                - ": ".length()
                - message.getChatMessage().length();

        return " ".repeat(Math.max(0, paddingLength - 1)) + "│";
    }
}
