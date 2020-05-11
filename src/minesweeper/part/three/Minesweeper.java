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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    
    @Override
    public void start(Stage primaryStage) {   
        this.minefield = new Minefield(rows, columns);
        this.minefield.populate(mines);
        
        this.stage = primaryStage;
        BorderPane root = new BorderPane();

        root.setTop(createMenus());
        
        grid = mineGrid();
        root.setCenter(grid);
        
        Scene scene = new Scene(root);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
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
        
        menus.getMenus().addAll(fileMenu, gameMenu);
        return menus;
    }
    
    private void saveMinefield() {
        System.out.println("Shit");
    }
        
    private void openMinefield() {
        System.out.println("Fuck");
    }
    
    private void newGame() {
        System.out.println("Ass");
    }
    
    private void revealMines() {
        for (Node n : grid.getChildren()) {  
            if (n instanceof MineButton) {
                MineButton button = (MineButton)n;
                int[] pos = button.getPosition();
                
                if (minefield.isMined(pos[0], pos[1])) {
                    button.setText("m");
                } else {
                    button.setText(String.valueOf(minefield.getMineNeighbour(pos[0], pos[1])));
                }
            }
        }
        
        // TODO Make popup show
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
                                revealMines();
                            }
                            minefield.printMinefield();
                            button.setDisable(true);
                        }
                        
                        
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        int[] p = button.getPosition();
                        System.out.printf("Mouse RIGHT clicked cell [%d, %d]%n", p[0], p[1]);
                        
                        if (!minefield.isMarked(p[0], p[1])) {
                            button.setText("h");
                            
                            // TODO check if all mines and only the mines are marked, if so return success
                            
                        } else {
                            button.setText(" ");
                        }
                        
                        minefield.mark(p[0], p[1]);
                    }
                });
                
                
                grid.add(button, c, r);
            }
        }
        
        return grid;
    }


    
}
