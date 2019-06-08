package tribe;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author conor
 */
public class TileStatsPopup {
    
    public static void display(String title, Tile t) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);

        Acre a = t.getAcre();
        Member m = t.getMember();
        Label label1 = new Label("Coordinates: null");
        Label label2 = new Label("Acre: null");
        Label label3 = new Label("Member: null");
        try {
            label1 = new Label("Coordinates: (" + a.getCords().getX() + ", " + a.getCords().getY() + ")");
            label2 = new Label("Acre: Passable " + a.getPassable() + ", Food " + a.getFood());
            label3 = new Label("Member: Str " + m.getStregth() + ", Born " + m.getBorn());
        } catch(NullPointerException e) {}

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, label2, label3);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
