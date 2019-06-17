package tribe;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public static final Color WATER = Color.rgb(0, 100, 190);
    
    
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
        buttonNewGame.setOnAction(e -> window.setScene(menuNewGame));
        Button buttonSavedGame = new Button();
        buttonSavedGame.setText("Saved Games");
        buttonSavedGame.setOnAction(e -> window.setScene(menuSavedGame));
        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(e -> window.close());
        
        // adding components to layout
        VBox layoutMenuMain = new VBox();
        layoutMenuMain.setAlignment(Pos.CENTER);
        layoutMenuMain.setSpacing(20);
        layoutMenuMain.getChildren().addAll(label1, buttonNewGame, buttonSavedGame, buttonExit);
        
        // adding layout to scene menuMain
        menuMain = new Scene(layoutMenuMain, 400, 200);
        
        // components for scene currentGame
        Button buttonNextYear1 = new Button("Next Year");
        buttonNextYear1.setOnAction(e -> newYear());
        Button buttonNextYear10 = new Button("Next Decade");
        buttonNextYear10.setOnAction(e -> newYear(10));
        Button buttonNextYear100 = new Button("Next Century");
        buttonNextYear100.setOnAction(e -> newYear(100));
        Button buttonChangeWorldSettings = new Button("Change Settings");
        buttonChangeWorldSettings.setOnAction(e -> updateGameWorldSettings(gw));
        Button buttonStats = new Button("Game stats");
        buttonStats.setOnAction(e -> showGlobalStats());
        Button buttonSave = new Button("Save Game");
        buttonSave.setOnAction(e -> saveWorldGetName());
        Button buttonMainMenu = new Button("Main Menu");
        buttonMainMenu.setOnAction(e -> window.setScene(menuMain));
        ToolBar toolBar = new ToolBar(buttonNextYear1, buttonNextYear10, buttonNextYear100, buttonChangeWorldSettings, buttonStats, buttonSave, buttonMainMenu);
        Canvas canvas = new Canvas(5 * WIDTH, 5 * HEIGHT);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int xCord = (int)e.getX() / 5;
                int yCord = (int)e.getY() / 5;
                Coordinate cord = new Coordinate(xCord, yCord);
                showTileStats(cord);
            }
        });
        gc = canvas.getGraphicsContext2D();
        drawGameWorld(gc);
        
        // adding components to layout
        BorderPane layoutCurrentGame = new BorderPane();
        layoutCurrentGame.setTop(toolBar);
        layoutCurrentGame.setLeft(canvas);
        
        // adding layout to scene currentGame
        currentGame = new Scene(layoutCurrentGame, 5 * WIDTH, 5 * HEIGHT + 45);
        
        // components for menuNewGame
        Label label2 = new Label("Settings");
        Label label3 = new Label("Number of Civilizations");
        Slider sliderCiv = new Slider(0, 4, 1);
        sliderCiv.setShowTickLabels(true);
        sliderCiv.setSnapToTicks(true);
        sliderCiv.setMajorTickUnit(1);
        sliderCiv.setBlockIncrement(1);
        sliderCiv.setMinorTickCount(0);
        Label label4 = new Label("Number of Nations per civilization");
        Slider sliderNat = new Slider(0, 10, 5);
        sliderNat.setShowTickLabels(true);
        sliderNat.setSnapToTicks(true);
        sliderNat.setMajorTickUnit(1);
        sliderNat.setBlockIncrement(1);
        sliderNat.setMinorTickCount(0);
        Label label5 = new Label("Number of Members per nation");
        Slider sliderMem = new Slider(0, 40, 20);
        sliderMem.setShowTickLabels(true);
        sliderMem.setSnapToTicks(true);
        sliderMem.setMajorTickUnit(4);
        sliderMem.setBlockIncrement(4);
        sliderMem.setMinorTickCount(3);
        Label label6 = new Label("Chance of Regrowing Food");
        Slider sliderRegrowRate = new Slider(0, 1, 0.1);
        sliderRegrowRate.setShowTickLabels(true);
        sliderRegrowRate.setSnapToTicks(true);
        sliderRegrowRate.setMajorTickUnit(0.1);
        sliderRegrowRate.setBlockIncrement(0.1);
        sliderRegrowRate.setMinorTickCount(1);
        Label label7 = new Label("Amount that Food Regrows");
        Slider sliderRegrowValue = new Slider(0, 1, 0.1);
        sliderRegrowValue.setShowTickLabels(true);
        sliderRegrowValue.setSnapToTicks(true);
        sliderRegrowValue.setMajorTickUnit(0.1);
        sliderRegrowValue.setBlockIncrement(0.1);
        sliderRegrowValue.setMinorTickCount(1);
        Button buttonStart = new Button("Start Game");
        // TODO: bed idea, passing window
        buttonStart.setOnAction(e -> newGameWorld(window, (int)sliderCiv.getValue(), (int)sliderNat.getValue(), (int)sliderMem.getValue(), sliderRegrowRate.getValue(), sliderRegrowValue.getValue(), gw.getWorldAge()));
        Button buttonBack1 = new Button("Back");
        buttonBack1.setOnAction(e -> window.setScene(menuMain));
        
        // adding components to layout
        VBox layoutMenuNewGame = new VBox();
        layoutMenuNewGame.setAlignment(Pos.CENTER);
        layoutMenuNewGame.setSpacing(20);
        layoutMenuNewGame.getChildren().addAll(label2, label3, sliderCiv, label4, sliderNat, label5, sliderMem, label6, sliderRegrowRate, label7, sliderRegrowValue, buttonStart, buttonBack1);
        
        // adding layout to scene menuNewGame
        menuNewGame = new Scene(layoutMenuNewGame, 400, 700);
        
        // components for scene menuSavedGame
        Label label8 = new Label("Saved Games");
        TableView<File> table = new TableView();
        Button buttonFileList = new Button("Refresh List");
        buttonFileList.setOnAction(e -> table.setItems(FXCollections.observableArrayList(updateSavedFiles())));
        TableColumn<File, String> columnName = new TableColumn<>("File Name");
        columnName.setMinWidth(50);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<File, Long> columnSize = new TableColumn<>("File Size in Bytes");
        columnSize.setMinWidth(50);
        columnSize.setCellValueFactory(new PropertyValueFactory<>("TotalSpace"));
        
        table.setItems(FXCollections.observableArrayList(updateSavedFiles()));
        table.getColumns().addAll(columnName, columnSize);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setFitToWidth(true);
        scrollpane.setFitToHeight(false);
        scrollpane.setPrefSize(200, 200);
        scrollpane.setContent(table);
        Button buttonLoad = new Button("Load Game");
        buttonLoad.setOnAction(e -> loadGameWorld(window, table.getSelectionModel().getSelectedItem()));
        Button buttonDelete = new Button("Delete Game");
        buttonDelete.setOnAction(e -> System.out.println("Game Deleted"));
        Button buttonBack2 = new Button("Back");
        buttonBack2.setOnAction(e -> window.setScene(menuMain));
        
        // adding components to layout
        VBox layoutMenuSavedGame = new VBox();
        layoutMenuSavedGame.setAlignment(Pos.CENTER);
        layoutMenuSavedGame.setSpacing(20);
        layoutMenuSavedGame.getChildren().addAll(label8, buttonFileList, scrollpane, buttonDelete, buttonLoad, buttonBack2);
        table.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount() >= 1) {}
        });
        
        // adding layout to scene menuSavedGame
        menuSavedGame = new Scene(layoutMenuSavedGame, 400, 400);
        
        window.setTitle("Tribe (300x164)");
        window.setResizable(false);
        window.setX(WIDTH);
        window.setY(HEIGHT);
        window.setScene(menuMain);
        window.show();
    }
    
    // untested!
    public void loadGameWorld(Stage window, File f) {
        if(f != null) {
            GameWorld gw = null;
            try {
                FileInputStream saveFile = new FileInputStream(f);
                ObjectInputStream restore = new ObjectInputStream(saveFile);
                gw = (GameWorld) restore.readObject();
                restore.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            newGameWorld(window, gw);
        }
        System.out.println("No Load Selected");
    }
    
    public void deleteGameWorld() {
        
    }
    
    public static ObservableList<File> updateSavedFiles() {
        ObservableList<File> FileList = FXCollections.observableArrayList();
        String dirName = "..\\Tribe\\src\\savedGames";
        File folder = new File(dirName);
        File[] fileList = folder.listFiles();
        for(File f : fileList) {
            FileList.add(f);
        }
        return FileList;
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
                    gc.setFill(WATER);
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
    
    // starts new game with given parameters (lies, actually adds members to current game)
    public void newGameWorld(Stage window, int popCiv, int popNat, int popMem, double regrowRate, double regrowValue, int worldAge) {
        GameWorld gameWorld = new GameWorld();
        gw = gameWorld;
        gw.updateLand(regrowRate, regrowValue);
        for(int i = 0; i < popCiv; i++) {
            gw.addCiv(popNat, popMem, worldAge);
        }
        window.setScene(currentGame);
    }
    
    public void newGameWorld(Stage window, GameWorld gameWorld) {
        gw = gameWorld;
        window.setScene(currentGame);
    }
    
    public void updateGameWorldSettings(GameWorld gw) {
        ChangeSettingsPopup.display("Change Game Settings", gw);
    }
    
    // opens GlobalStats window
    public void showGlobalStats() {     
        GlobalStatsPopup.display("Global Statistics", gw);
    }
    
    // opens TileStats window
    public void showTileStats(Coordinate c) {
        Member m = gw.findMember(c);
        Acre a = gw.getLand().getAcre(c.getX(), c.getY());
        Tile t = new Tile(m, a);
        TileStatsPopup.display("Tile Statistics", t);
    }
    
    public void saveWorldGetName() {
        SaveWorldPopup.display("Save World", gw);
    }
    
    // moves all members and redraws canvas n times
    public void newYear() {
        gw.generateNewYear();
        drawGameWorld(gc);
    }
    public void newYear(int n) {
        for(int i = 0; i < n; i++) {
            newYear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
