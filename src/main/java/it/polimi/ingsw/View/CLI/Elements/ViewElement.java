package it.polimi.ingsw.View.CLI.Elements;

import java.util.ArrayList;

public abstract class ViewElement {
    String NULL = ".";

    abstract ArrayList<String> getPrint(ArrayList<String> output);
}
