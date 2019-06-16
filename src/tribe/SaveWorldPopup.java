package tribe;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author conor
 */
public class SaveWorldPopup {
    public static void display(String title, GameWorld gameWorld) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        Label label1 = new Label("Save File Name");
        GridPane.setConstraints(label1, 0, 0);
        TextField tf1 = new TextField("FileName.sav");
        GridPane.setConstraints(tf1, 0, 1);
        Button buttonSave = new Button("Save");
        buttonSave.setOnAction(e -> SaveGameWorld.SaveGameWorld(tf1.getText(), gameWorld, window));
        GridPane.setConstraints(buttonSave, 1, 1);
        
        grid.getChildren().addAll(label1, tf1, buttonSave);
        
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }
}
