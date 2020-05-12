/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.part.three;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 *
 * @author 
 */
public class Minesweeper extends Application {
    GridPane grid;
    Minefield minefield;
    int rows = 10;
    int columns = 15;
    int mines = 20; 
    private TabPane tabPane = new TabPane();
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    BorderPane mainBorderPane;
    
    @Override
    public void start(Stage primaryStage) {   
        this.minefield = new Minefield(rows, columns);
        this.minefield.populate(mines);
        
        this.stage = primaryStage;
        mainBorderPane = new BorderPane();

        mainBorderPane.setTop(createMenus());
        
        mainBorderPane.setCenter(createButtonMenu());
        
        grid = mineGrid();
        mainBorderPane.setBottom(grid);
        
        Scene scene = new Scene(mainBorderPane);

        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
 }
    
    private MenuBar createMenus() {
        MenuBar menus = new MenuBar();
        
        Menu fileMenu = new Menu("File");
        
        MenuItem saveItem = new MenuItem("Save...");
        saveItem.setOnAction(e -> {
            saveMinefield();
        });
        
        MenuItem openItem = new MenuItem("Open...");
        openItem.setOnAction(e -> {
            openMinefield();
        });
        
        fileMenu.getItems().addAll(openItem, saveItem);
        
        Menu gameMenu = new Menu("Game");
        
        MenuItem newItem = new MenuItem("New...");
        newItem.setOnAction(e -> {
            newGame();
        });
        
        gameMenu.getItems().addAll(newItem); 
        
        Menu debugMenu = new Menu("Debug");
        
        MenuItem showItem = new MenuItem("Show...");
        showItem.setOnAction(e -> {
            showMinefield();
        });
        
        debugMenu.getItems().addAll(showItem); 
        
        menus.getMenus().addAll(fileMenu, gameMenu, debugMenu);
        return menus;
    }
    
    private HBox createButtonMenu() {
    Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button hardButton = new Button("Hard");
        Button customButton = new Button("Custom");
        
        easyButton.setOnAction(e -> {
            rows = 10;
            columns = 15;
            mines = 20;
            
            minefield = new Minefield(rows, columns);
            minefield.populate(mines);
                    
            grid = mineGrid();
            mainBorderPane.setBottom(grid);
        });
        
        mediumButton.setOnAction(e -> {
            rows = 20;
            columns = 25;
            mines = 50;
            
            minefield = new Minefield(rows, columns);
            minefield.populate(mines);
                    
            grid = mineGrid();
            mainBorderPane.setBottom(grid);
        });
        
        hardButton.setOnAction(e -> {
            rows = 30;
            columns = 35;
            mines = 100;
            
            minefield = new Minefield(rows, columns);
            minefield.populate(mines);
                    
            grid = mineGrid();
            mainBorderPane.setBottom(grid);
        });
        
        customButton.setOnAction(e -> {
            newGame();
        });
        
        HBox buttonPane = new HBox();
        buttonPane.getChildren().addAll(easyButton, mediumButton, hardButton, customButton);
        
        return buttonPane;
    }
    
    private void showMinefield() {
        for (Node n : grid.getChildren()) {  
            if (n instanceof MineButton) {
                MineButton button = (MineButton) n;
                int[] pos = button.getPosition();
                
                if (minefield.isMined(pos[0], pos[1])) {
                    button.setText("m");
                } else {
                    button.setText(String.valueOf(minefield.getMineNeighbour(pos[0], pos[1])));
                }       
            }
        }
    }
    
    private void saveMinefield() {
        System.out.println("Shit");
    }
    
    private void openMinefield() {
        System.out.println("Fuck");
    }
    
    private void newGame() {
        newGamePopup();
    }
    
    private void fail() {
        for (Node n : grid.getChildren()) {  
            if (n instanceof MineButton) {
                MineButton button = (MineButton)n;
                int[] pos = button.getPosition();
                
                if (minefield.isMined(pos[0], pos[1])) {
                    button.setText("m");
                } else {
                    button.setText(String.valueOf(minefield.getMineNeighbour(pos[0], pos[1])));
                }
                
                button.setDisable(true);
            }
        }
        
        // TODO Make popup show
        successOrFailPopup(false);
    }

    private void success() {
    // TODO check if all mines and only the mines are marked, if so return success
    for (Node n : grid.getChildren()) {
            if (n instanceof MineButton) {
                n.setDisable(true);
            }    
        }
    
        // TODO Make popup show
        successOrFailPopup(true);
    }
    
    public void successOrFailPopup(boolean success) {
        Stage popupStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        
        //Creating a Text object 
        Text topText = new Text();
        Text centerText = new Text();     

        if (success) {
            popupStage.setTitle("Success!");
            topText.setText("Congratulations");
            centerText.setText("You won!");
            
        } else {
            popupStage.setTitle("Failed!");
            topText.setText("Commiserations");
            centerText.setText("You Lost :c");
        }

        //Setting the top, bottom, center, right and left nodes to the pane 
        root.setTop(topText); // congrats text
        root.setCenter(centerText); // You won!
        // new game
            
        popupStage.setScene(scene);
        popupStage.show();
    }
    
    private void newGamePopup() {
        
        Stage popupStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        
        TextField entryRows = new TextField();
        TextField entryColumns = new TextField();
        TextField entryMines = new TextField();
        
        TilePane fieldPane = new TilePane();
        fieldPane.setPrefColumns(2);
        
        fieldPane.getChildren().addAll();
        fieldPane.getChildren().addAll(new Label("Rows:"), entryRows, new Label("Columns:"),entryColumns,new Label("Mines:"),entryMines);
                
        Button goButton = new Button("Go!");
        goButton.setOnAction(e -> {            
            String regex = "\\d+";
            
            String r = entryRows.getText();
            String c = entryColumns.getText();
            String m = entryMines.getText();
            
            if (r.matches(regex) && c.matches(regex) && m.matches(regex)) {
                int totalSpaces;
                totalSpaces = (Integer.parseInt(r) * Integer.parseInt(c)) - 1;
                
                if (Integer.parseInt(m) <= totalSpaces) {
                    rows = Integer.parseInt(r);
                    columns = Integer.parseInt(c);
                    mines = Integer.parseInt(m);
                    
                    minefield = new Minefield(rows, columns);
                    minefield.populate(mines);
                    
                    grid = mineGrid();
                    mainBorderPane.setBottom(grid);
                    
                    popupStage.close();
                
                } else {
                    root.setTop(new Text("Error: Too many mines"));
                }
            } else {
                root.setTop(new Text("Error: Please enter digits"));
            }
        });
        
        
        HBox buttonPane = new HBox();
        
        buttonPane.getChildren().add(goButton);
        root.setBottom(buttonPane);
        
        root.setCenter(fieldPane);
        
        popupStage.setScene(scene);
        popupStage.show();
        
    }
    
    private void refresh() {
        // loop through buttons setting text and checking if others are visable
        for (Node n : grid.getChildren()) {
            
            if (n instanceof MineButton) {
                MineButton button = (MineButton)n;
                int[] pos = button.getPosition();

                if (minefield.isRevealed(pos[0], pos[1])) {
                    button.setDisable(true);

                    if (minefield.isMined(pos[0], pos[1])) {
                        button.setText("m");
                    } else {
                        button.setText(String.valueOf(minefield.getMineNeighbour(pos[0], pos[1])));
                    }
                } else if (minefield.isMarked(pos[0], pos[1])) {
                    button.setText("h");
                } else {
                    button.setText(" ");
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private GridPane mineGrid() {
        GridPane grid = new GridPane();

        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                MineButton button = new MineButton(" ", r, c);       
                
                button.setOnMousePressed( e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        int[] p = button.getPosition();
                        System.out.printf("Mouse LEFT clicked cell [%d, %d]%n", p[0], p[1]);
                        
                        if (!minefield.isMarked(p[0], p[1])) {
                            boolean b = minefield.step(p[0], p[1]);
                            System.out.println(b);
                            if (b == true) {
                                button.setText(String.valueOf(minefield.getMineNeighbour(p[0], p[1])));
                                refresh();
                            } else if (b == false) {
                                button.setText("m");
                                fail();
                            }
                            minefield.printMinefield();
                            button.setDisable(true);
                        }
                        
                        
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        int[] p = button.getPosition();
                        System.out.printf("Mouse RIGHT clicked cell [%d, %d]%n", p[0], p[1]);
                        
                        if (!minefield.isMarked(p[0], p[1])) {
                            button.setText("h");
                            
                        } else {
                            button.setText(" ");
                        }
                        
                        minefield.mark(p[0], p[1]);
                        
                        // TODO check if all mines and only the mines are marked, if so return success
                        if (minefield.areAllMinesFound()) {
                            success();
                        }
                    }
                });
                
                
                grid.add(button, c, r);
            }
        }
        
        return grid;
    }
}
