/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.part.three;
import java.awt.Insets;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    Minefield mines;
    private TabPane tabPane = new TabPane();
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {   
        this.mines = new Minefield(10, 15);
        this.mines.populate(20);
        
        this.stage = primaryStage;
        BorderPane root = new BorderPane();

        root.setTop(createMenus());
        root.setCenter(mineGrid());
        
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
    
    private void refresh() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private GridPane mineGrid() {
        GridPane grid = new GridPane();

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                Button button = new Button(String.valueOf(1));
                grid.add(button, c, r);
            }
        }
        
        return grid;
    }
    
}
