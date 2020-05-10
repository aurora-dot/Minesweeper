/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.part.three;

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
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author epann
 */
public class Minesweeper extends Application {
    
    private TabPane tabPane = new TabPane();
    FileChooser fileChooser = new FileChooser();
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {    
        this.stage = primaryStage;
        BorderPane root = new BorderPane();

        root.setTop(createMenus());

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
        openItem.setOnAction(e -> {
            newGame();
        });
        
        gameMenu.getItems().addAll(newItem); 
        
        menus.getMenus().addAll(fileMenu, gameMenu);
        return menus;
    }
    
    private void saveMinefield() {

    }
        
    private void openMinefield() {

    }
    
    private void newGame() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
