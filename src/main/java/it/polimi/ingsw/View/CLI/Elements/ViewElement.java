package it.polimi.ingsw.View.CLI.Elements;

import java.util.ArrayList;

public interface ViewElement {
    String NULL = ".";
    ArrayList<String> getPrint(ArrayList<String> output);
}
