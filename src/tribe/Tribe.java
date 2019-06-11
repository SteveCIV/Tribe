package tribe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
 *
 * @author conor
 */
public class Tribe extends Application {
    
    public static final int WIDTH = 300;
    public static final int HEIGHT = 164;
    public static GameWorld gw;
    
    public static final Color RAINFOREST = Color.rgb(28, 178, 66);
    public static final Color SEASONALFOREST = Color.rgb(77, 255, 77);
    public static final Color SAVANNA = Color.rgb(144, 255, 77);
    public static final Color DESERT = Color.rgb(212, 255, 77);
    public static final Color WASTELAND = Color.rgb(166, 166, 157);
    
    
    Stage window;
    Scene menuMain, menuNewGame, menuSavedGame, currentGame;
    GraphicsContext gc;
    
    @Override
    public void start(Stage window) throws Exception {
        gw = new GameWorld();
        
        // components for scene menuMain
        Label label1 = new Label("Welcome to Tribe");
        Button buttonNewGame = new Button();
        buttonNewGame.setText("New game");
        buttonNewGame.setOnAction(e -> window.setScene(menuNewGame)); // TODO: will go to new game menu
        Button buttonSavedGame = new Button();
        buttonSavedGame.setText("Saved Games");
        buttonSavedGame.setOnAction(e -> window.setScene(currentGame)); // TODO: will go to saved games menu
        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(e -> window.close());
        
        // adding components to layout
        VBox layout1 = new VBox();
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(20);
        layout1.getChildren().addAll(label1, buttonNewGame, buttonSavedGame, buttonExit);
        
        // adding layout to scene menuMain
        menuMain = new Scene(layout1, 400, 200);
        
        // components for scene currentGame
        Button buttonNextYear1 = new Button();
        buttonNextYear1.setText("Next Year (x  1)");
        buttonNextYear1.setOnAction(e -> newYear());
        Button buttonNextYear10 = new Button();
        buttonNextYear10.setText("Next Year (x 10)");
        buttonNextYear10.setOnAction(e -> newYear(10));
        Button buttonNextYear100 = new Button();
        buttonNextYear100.setText("Next Year (x100)");
        buttonNextYear100.setOnAction(e -> newYear(100));
        Button buttonStats = new Button();
        buttonStats.setText("Game stats");
        buttonStats.setOnAction(e -> showGlobalStats());
        Button buttonMainMenu = new Button();
        buttonMainMenu.setText("Main Menu");
        buttonMainMenu.setOnAction(e -> window.setScene(menuMain));
        ToolBar toolBar = new ToolBar(buttonNextYear1, buttonNextYear10, buttonNextYear100, buttonStats, buttonMainMenu);
        
        // canvas component for currentGame
        Canvas canvas = new Canvas(5 * WIDTH, 5 * HEIGHT);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int xCord = (int)e.getX() / 5;
                int yCord = (int)e.getY() / 5;
                
                // finds member
                Coordinate cord = new Coordinate(xCord, yCord);
                Member m = gw.findMember(cord);
                
                // fins acre
                Acre a = gw.getLand().getAcre(xCord, yCord);
                Tile tile = new Tile(m, a);
                showTileStats(tile);
            }
        });
        gc = canvas.getGraphicsContext2D();
        drawGameWorld(gc);
        
        // adding components to layout
        BorderPane layoutActiveSave = new BorderPane();
        layoutActiveSave.setTop(toolBar);
        layoutActiveSave.setLeft(canvas);
        
        // adding layout to scene currentGame
        currentGame = new Scene(layoutActiveSave, 5 * WIDTH, 5 * HEIGHT + 45);
        
        // components for menuNewGame
        Label label2 = new Label("New Tribe");
        Label label3 = new Label("Number of Civilizations");
        Slider sliderCiv = new Slider(0, 4, 1);
        sliderCiv.setShowTickLabels(true);
        sliderCiv.setMajorTickUnit(1);
        sliderCiv.setBlockIncrement(1);
        Label label4 = new Label("Number of Nations per civilization");
        Slider sliderNat = new Slider(0, 10, 1);
        sliderNat.setShowTickLabels(true);
        sliderNat.setMajorTickUnit(1);
        sliderNat.setBlockIncrement(1);
        Label label5 = new Label("Number of Members per nation");
        Slider sliderMem = new Slider(0, 40, 1);
        sliderMem.setShowTickLabels(true);
        sliderMem.setMajorTickUnit(1);
        sliderMem.setBlockIncrement(1);
        Button buttonStart = new Button();
        buttonStart.setText("Start Game");
        buttonStart.setOnAction(e -> startNewGame(window, (int)sliderCiv.getValue(), (int)sliderNat.getValue(), (int)sliderMem.getValue(), gw.getWorldAge())); //TODO: bad idea, passing window
        Button buttonBack = new Button();
        buttonBack.setText("Back");
        buttonBack.setOnAction(e -> window.setScene(menuMain));
        
        // adding components to layout
        VBox layout2 = new VBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(20);
        layout2.getChildren().addAll(label2, label3, sliderCiv, label4, sliderNat, label5, sliderMem, buttonStart, buttonBack);
        
        // adding layout to scene menuNewGame
        menuNewGame = new Scene(layout2, 400, 600);
        
        window.setTitle("Tribe (300x164)");
        window.setResizable(false);
        window.setX(WIDTH);
        window.setY(HEIGHT);
        window.setScene(menuMain);
        window.show();
    }
    
    // draws all gw layers to canvas
    private void drawGameWorld(GraphicsContext gc) {
        drawTerrain(gc);
        drawCivilizations(gc);
    }
    
    // draws gw.land to canvas
    private void drawTerrain(GraphicsContext gc) {
        Map land = gw.getLand();
        for(int i = 0; i < 300; i++) {
            for(int j = 0; j < 164; j++) {
                Acre acre = land.getAcre(i, j);
                
                if(acre.getPassable()) {
                    if(acre.getFood() > 0.75) {
                        gc.setFill(RAINFOREST);
                    } else if(acre.getFood() > 0.5) {
                        gc.setFill(SEASONALFOREST);
                    } else if(acre.getFood() > 0.25) {
                        gc.setFill(SAVANNA);
                    } else if(acre.getFood() > 0.0){
                        gc.setFill(DESERT);
                    } else {
                        gc.setFill(WASTELAND);
                    }
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
    public void drawCivilizations(GraphicsContext gc) {
        // for every civilization of a gameworld
        for(Civilization c : gw.getCivList()) {
            // for every nation of a civilization
            for(Nation n : c.getNationList()) {
                gc.setFill(n.NCOLOR);
                // of every member of a nation print their color to canvas
                for(Member m : n.getMemberList()) {
                    int i = m.getCords().getX();
                    int j = m.getCords().getY();
                    gc.fillOval(i * 5, j * 5, 5, 5);
                }
            }
        }
    }
    
    // starts new game with given parameters
    public void startNewGame(Stage window, int popCiv, int popNat, int popMem, int worldAge) {
        for(int i = 0; i < popCiv; i++) {
            gw.addCiv(popNat, popMem, worldAge);
        }
        window.setScene(currentGame);
    }
    
    // opens GlobalStats window
    public void showGlobalStats() {
        int numCiv = 0;
        int numNat = 0;
        int numMem = 0;
        for(Civilization c : gw.getCivList()) {
            for(Nation n : c.getNationList()) {
                numMem += n.getNationPop();
                numNat++;
            }
            numCiv++;
        }
        
        GlobalStatsPopup.display("Global Statistics", numCiv, numNat, numMem, gw.getWorldAge());
    }
    
    // opens TileStats window
    public void showTileStats(Tile t) {
        TileStatsPopup.display("Tile Statistics", t);
    }
    
    // moves all members of all nations and redraws canvas
    public void newYear() {
        gw.generateNewYear();
        drawGameWorld(gc);
    }
    
    // n years pass given n
    public void newYear(int n) {
        for(int i = 0; i < n; i++) {
            newYear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
