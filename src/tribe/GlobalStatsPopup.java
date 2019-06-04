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
public class GlobalStatsPopup {
    
    public static void display(String title, int civNumber, int natNumber, int memNumber, int worldAge) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);
        
        Label label1 = new Label("Number of Civilizations: " + civNumber);
        Label label2 = new Label("Number of Nations:" + natNumber);
        Label label3 = new Label("Population of the world: " + memNumber);
        Label label4 = new Label("World Age: " + worldAge);
                
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, label2, label3, label4);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
