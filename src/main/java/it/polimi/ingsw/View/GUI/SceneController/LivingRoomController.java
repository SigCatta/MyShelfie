package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class LivingRoomController {

    @FXML
    StackPane place1;
    @FXML
    StackPane slot1;

    /**
     * Moves imageView from place1 to slot1
     *
     * @param mouseEvent mouseClicked event
     */
    public void moveToSlot(MouseEvent mouseEvent) {
        if(place1.getChildren().size() == 0) return;
        ImageView imageView = (ImageView) place1.getChildren().get(0); // get the image view in place1
        place1.getChildren().clear(); // remove the image view from place1
        slot1.getChildren().add(imageView); // add the image view to slot1
    }

    /**
     * Moves imageView from slot1 to place1
     *
     * @param mouseEvent mouseClicked event
     */
    public void moveToPlace(MouseEvent mouseEvent){
        if(slot1.getChildren().size() == 0) return;
        ImageView imageView = (ImageView) slot1.getChildren().get(0);
        slot1.getChildren().clear();
        place1.getChildren().add(imageView);
    }

}


