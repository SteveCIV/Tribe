package tribe;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author conor
 */
public class ChangeSettingsPopup {
    public static void display(String title, GameWorld gw) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(700);
        
        Label label2 = new Label("Change Settings");
        Label label3 = new Label("Number of Civilizations to Add");
        Slider sliderCiv = new Slider(0, 4, 1);
        sliderCiv.setShowTickLabels(true);
        sliderCiv.setSnapToTicks(true);
        sliderCiv.setMajorTickUnit(1);
        sliderCiv.setBlockIncrement(1);
        sliderCiv.setMinorTickCount(0);
        Label label4 = new Label("Number of Nations per civilization to Add");
        Slider sliderNat = new Slider(0, 10, 5);
        sliderNat.setShowTickLabels(true);
        sliderNat.setSnapToTicks(true);
        sliderNat.setMajorTickUnit(1);
        sliderNat.setBlockIncrement(1);
        sliderNat.setMinorTickCount(0);
        Label label5 = new Label("Number of Members per nation to Add");
        Slider sliderMem = new Slider(0, 40, 20);
        sliderMem.setShowTickLabels(true);
        sliderMem.setSnapToTicks(true);
        sliderMem.setMajorTickUnit(4);
        sliderMem.setBlockIncrement(4);
        sliderMem.setMinorTickCount(3);
        Label label6 = new Label("New Chance of Regrowing Food");
        Slider sliderRegrowRate = new Slider(0, 1, 0.1);
        sliderRegrowRate.setShowTickLabels(true);
        sliderRegrowRate.setSnapToTicks(true);
        sliderRegrowRate.setMajorTickUnit(0.1);
        sliderRegrowRate.setBlockIncrement(0.1);
        sliderRegrowRate.setMinorTickCount(1);
        Label label7 = new Label("New Amount that Food Regrows");
        Slider sliderRegrowValue = new Slider(0, 1, 0.1);
        sliderRegrowValue.setShowTickLabels(true);
        sliderRegrowValue.setSnapToTicks(true);
        sliderRegrowValue.setMajorTickUnit(0.1);
        sliderRegrowValue.setBlockIncrement(0.1);
        sliderRegrowValue.setMinorTickCount(1);
        Button buttonCommitChanges = new Button("Commit Changes");
        // TODO: bed idea, passing window
        buttonCommitChanges.setOnAction(e -> updateGameWorld(window, gw, (int)sliderCiv.getValue(), (int)sliderNat.getValue(), (int) sliderMem.getValue(), sliderRegrowRate.getValue(), sliderRegrowValue.getValue()));

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.getChildren().addAll(label2, label3, sliderCiv, label4, sliderNat, label5, sliderMem, label6, sliderRegrowRate, label7, sliderRegrowValue, buttonCommitChanges);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    public static void updateGameWorld(Stage window, GameWorld gw, int numCiv, int numNat, int numMem, double regrowRate, double regrowValue) {
        gw.updateLand(regrowRate, regrowValue);
        for (int i = 0; i < numCiv; i++) {
            gw.addCiv(numNat, numMem, gw.getWorldAge());
        }
        window.close();
    }
}
