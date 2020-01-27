package com.mycompany.arkanoid;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    int Scene_width = 340;
    int Scene_height = 420;
    short stickHeight = 50;
    
    @Override
    public void start(Stage stage) {
        
        Pane root = new Pane();
        var scene = new Scene(root, Scene_width, Scene_height);
        stage.setScene(scene);
        stage.show();
        
        //fondo del juego
        Image fondo = new Image(getClass().getResourceAsStream("/imagenes/fondo.png"));
        ImageView imageView1 = new ImageView(fondo);
        root.getChildren().add(imageView1);
        
        //barra de control
        Image barra = new Image(getClass().getResourceAsStream("/imagenes/barracontrol.PNG"));
        ImageView imageView2 = new ImageView(barra);
        root.getChildren().add(imageView2);
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Timeline timeline = new Timeline(
        
        
        
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}




