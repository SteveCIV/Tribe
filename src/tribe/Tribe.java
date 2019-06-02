package tribe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *
 * @author conor
 */
public class Tribe extends Application {
    
    public static final int WIDTH = 300;
    public static final int HEIGHT = 164;
    public static GameWorld gw;
    
    Stage window;
    Scene mainMenu, initalWorld;
    GraphicsContext gc;
    
    @Override
    public void start(Stage window) throws Exception {
        
        // components for scene1
        Label label1 = new Label("Welcome to Tribe");
        Button button1 = new Button();
        button1.setText("Start new game");
        button1.setOnAction(e -> window.setScene(initalWorld));
        
        // adding components to layout
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label1, button1);
        
        // adding layout to scene mainMenu
        mainMenu = new Scene(layout1, 200, 200);
        
        // will be replaced with user provied input
        gw = new GameWorld();
        gw.setNewNation(1000, gw.getWorldAge());
        
        // components for scene2
        Button button2 = new Button();
        button2.setText("Next Year (x 1)");
        button2.setOnAction(e -> newYear());
        Button button3 = new Button();
        button3.setText("Next Year (x10)");
        button3.setOnAction(e -> newYear(10));
        Button button4 = new Button();
        button4.setText("Main Menu");
        button4.setOnAction(e -> window.setScene(mainMenu));
        ToolBar toolBar = new ToolBar(button2, button3, button4);
        
        // canvas component for scene2
        Canvas canvas = new Canvas(5 * WIDTH, 5 * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        drawGameWorld(gc);
        
        // adding components to layout
        BorderPane layout2 = new BorderPane();
        layout2.setTop(toolBar);
        layout2.setLeft(canvas);
        
        // adding layout to scene initalWorld
        initalWorld = new Scene(layout2, 5 * WIDTH, 5 * HEIGHT + 45);
        
        window.setTitle("Tribe (300x164)");
        window.setScene(mainMenu);
        window.show();
    }
    
    // draws all gw layers to canvas
    private void drawGameWorld(GraphicsContext gc) {
        drawTerrain(gc);
        drawCivilization(gc);
    }
    
    // draws gw.land to canvas
    private void drawTerrain(GraphicsContext gc) {
        Map land = gw.getLand();
        for(int i = 0; i < 300; i++) {
            for(int j = 0; j < 164; j++) {
                Acre acre = land.getTile(i, j);
                
                if(acre.getPassable()) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(i * 5, j * 5, 5, 5);
                }
                if(!acre.getPassable()) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i * 5, j * 5, 5, 5);
                }
            }
        }
    }
    
    // draws gw.civ to canvas
    public void drawCivilization(GraphicsContext gc) {
        Civilization civ = gw.getCiv();
        
        // for every nation of a civilization
        for(Nation n : civ.getNationList()) {
            
            // of every member of a nation print their color to canvas
            for(Member m : n.getMemberList()) {
                int i = m.getCords().getX();
                int j = m.getCords().getY();
                gc.setFill(Color.RED);
                gc.fillRect(i * 5, j * 5, 5, 5);
            }
        }
    }
    
    // moves all members of all nations and redraws canvas
    public void newYear() {
        gw.generateNewYear();
        drawGameWorld(gc);
    }
    
    // for i step that many years
    public void newYear(int years) {
        for(int i = 0; i < years; i++) {
            newYear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
