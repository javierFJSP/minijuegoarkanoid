package com.mycompany.arkanoid;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    int Scene_width = 240;
    int Scene_height = 320;
    
    @Override
    public void start(Stage stage) {
        
        Pane root = new Pane();
        var scene = new Scene(root, Scene_width, Scene_height);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
        
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}