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
    
    public static void display(String title, GameWorld gw) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(350);
        int numCiv = 0;
        int numNat = 0;
        int numMem = 0;
        for (Civilization c : gw.getCivList()) {
            for (Nation n : c.getNationList()) {
                numMem += n.getPopNation();
                numNat++;
            }
            numCiv++;
        }
        double regrowRate = gw.getLand().getRegrowRate();
        double regrowValue =  gw.getLand().getRegrowValue();
        double avgFood = gw.getLand().getAverageFood();
        int totalPassable = gw.getLand().getTotalPassable();
        
        Label label1 = new Label("World Age: " + gw.getWorldAge());
        Label label2 = new Label("Number of Civilizations: " + numCiv);
        Label label3 = new Label("Number of Nations: " + numNat);
        Label label4 = new Label("Number of Members: " + numMem);
        Label label5 = new Label("Food Regrowth Rate: " + regrowRate);
        Label label6 = new Label("Food Regrowth Value: " + regrowValue);
        Label label7 = new Label("Average Food per Tile: " + avgFood);
        Label label8 = new Label("Estemated Carrying Capacity: " + (totalPassable * regrowRate) * (regrowValue / 0.1));
                
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, label8);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
