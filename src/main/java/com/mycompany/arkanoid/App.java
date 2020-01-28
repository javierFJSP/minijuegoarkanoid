package com.mycompany.arkanoid;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    int Scene_width = 340;
    int Scene_height = 420;
    short stickHeight = 50;
    
    short ballCenterX = 0;
    byte ballCurrentSpeedX = 3;
    byte ballDirectionX = 1;
    
    short ballCenterY = 0;
    byte ballCurrentSpeedY = 3;
    byte ballDirectionY = 1;
    
    short palaPosX = (short)((Scene_height)/2);
    byte palaCurrentSpeed = 3;
    byte palaDirectionX = 0;
   // short palaPosX
    
    // Cuadros de texto para las puntuaciones
    Text textScore;
    Text textHighScore;
    // Puntuación actual
    int score;
    // Puntuación máxima
    int highScore;
    
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
        
        
        //pelota
        Circle circleBall = new Circle();
        circleBall.setCenterX(50);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);  
        circleBall.setFill(Color.RED);
        root.getChildren().add(circleBall);
        
        //barra de control
        Image barra = new Image(getClass().getResourceAsStream("/imagenes/barracontrol.PNG"));
        ImageView imageView2 = new ImageView(barra);
        imageView2.setX(palaPosX);
        root.getChildren().add(imageView2);
    
        //control de la barra <- o ->
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                switch(keyEvent.getCode()) {
                    case RIGHT:
                        palaDirectionX = 1;
                        break;
                    case LEFT:
                        palaDirectionX = -1;
                        break;
                }                
            }
        });
        
        
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                palaDirectionX = 0;
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    // ANIMACIÓN DE LA BOLA
                    circleBall.setCenterX(ballCenterX);
                    circleBall.setCenterY(ballCenterY);
                    ballCenterX += ballCurrentSpeedX * ballDirectionX;
                    ballCenterY += ballCurrentSpeedY * ballDirectionY;
                    // Control de rebote horizontal
                    if(ballCenterX >= 340) {
                        ballCenterX = 10;
                        ballDirectionX = 1;
                    } else if(ballCenterX <= 0){
                        ballDirectionX = 1;
                    }
                    // Control de rebote vertical  
                    if(ballCenterY >= Scene_height) {
                        
                        ballDirectionY = -1;
                    } else if(ballCenterY <= 0){
                        ballDirectionY = 1;
                    }
                    // DETECCIÓN DE COLISIÓN 
//                    Shape shapeCollision = Shape.intersect(circleBall, circleBall);
//                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
//                    if(colisionVacia == false && ballDirectionX == 1) {
//                        ballDirectionX = -1;
//                        score++;
//                        textScore.setText(String.valueOf(score));
//                    }
                    // ANIMACIÓN DE LA PALA
                    imageView2.setX(palaPosX);
                    palaPosX += palaCurrentSpeed * palaDirectionX;
                    if(palaPosX <= 0 || palaPosX >= Scene_height-stickHeight) {
                        palaDirectionX = 0;
                    }
                    if(palaPosX <= 0) {
                        palaDirectionX = 0;
                        palaPosX = 0;
                    } else if (palaPosX >= Scene_height-stickHeight) {
                        palaDirectionX = 0;
                        palaPosX = (short)(Scene_height-stickHeight);
                    }
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}




