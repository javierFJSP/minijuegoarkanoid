package com.mycompany.arkanoid;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    
    short ballCenterY = 30;
    byte ballCurrentSpeedY = 3;
    byte ballDirectionY = 1;
    
    short palaPosX = (short)((Scene_height)/2);
    short palaPosY = 400;
    byte palaCurrentSpeed = 3;
    byte palaDirectionX = 0;
    byte palaDirectionY = 0;
    
  //variables ladrillos  
    int brickRowCount = 3;
    int brickColumnCount = 5;
    int brickWidth = 75;
    int brickHeight = 20;
    int brickPadding = 10;
    int brickOffsetTop = 30;
    int brickOffsetLeft = 30;
    
    
    
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
        
//        HBox hbox = new HBox();
//        root.getChildren().add(hbox);
        
//        Button button1 = new Button("Terminar partida");
//        hbox.getChildren().add(button1);
        
//        VBox vbox = new VBox();
//        hbox.getChildren().add(vbox);
 //       vbox.setVisible(false);
        
 //       Label label1 = new Label("Se acabo la partida");
 //       vbox.getChildren().add(label1);
        
 //       Button button2 = new Button("Reiniciar la partida");
 //       vbox.getChildren().add(button2);
        
//        button1.setOnAction((ActionEvent e) -> {
//            terminarPartida();
//        });
        
 //       button2.setOnAction((ActionEvent e) -> {
 //           reiniciarPartida();
 //       });
        
 //       private void terminarPartida() {
 //               vbox.setVisible(true);
 //               button1.setVisible(false);
 //       };
        
 //       };private void reiniciarPartida() {
 //               vbox.setVisible(true);
 //               button1.setVisible(false);
 //       };
        
        
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
   //     RECTANGLE  =;
//        Image barra = new Image(getClass().getResourceAsStream("/imagenes/barracontrol.PNG"));
//        ImageView imageViewPala = new ImageView(barra);
//        imageViewPala.setX(palaPosX);
//        imageViewPala.setY(palaPosY);
//        root.getChildren().add(imageViewPala);
        
        Rectangle rectPala = new Rectangle();
        rectPala.setWidth(50);
        rectPala.setHeight(10);
        rectPala.setX(palaPosX);
        rectPala.setY(palaPosY);
        rectPala.setFill(Color.GREEN);        
        root.getChildren().add(rectPala);
        
        
        //int bricks = [];
        //    for(c=0; c<brickColumnCount; c++) {
        //        bricks[c] = [];
        //        for(r=0; r<brickRowCount; r++) {
        //            bricks[c][r] = { x: 0, y: 0 };
        //        }
        //    }
        
        
//        Group groupPala = new Group();
//        groupPala.getChildren().add(rectPala);
//        groupPala.getChildren().add(imageViewPala);
//        root.getChildren().add(groupPala);
 
    
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
                    if(ballCenterX >= Scene_width) {
                      
                        ballDirectionX = -1;
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
                    Shape shapeCollision = Shape.intersect(circleBall, rectPala);
                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
                    if(colisionVacia == false) {
                        ballDirectionX = 1;
                        score++;
                        textScore.setText(String.valueOf(score));
                    }
                    
                    
 //                   private int getStickCollisionZone(Circle ball, Rectangle stick) {
 //                       if (shape.intersect(ball, stick)getBoundInLocal().isEmpty()) {
 //                           return 0;
 //                       } else {
 //                           double offsetBallStick = ball.getCenterY()- stick.getY();
 //                           if(offsetBallStick < stick.getHeight() * 0.1) {
 //                               return 1;
 //                           } else if(offsetBallStick < stick.getHeight() / 2) {
 //                               return 2;
 //                           } else if(offsetBallStick >= stick.getHeight() / 2 && offsetBallStick < stick.getHeight() * 0.9) {
  //                              return 3;
 //                           } else {
 //                               return 4;
 //                           }
 //                       }
 //                   }
                    
 //                   int collisionZone = getStickCollisionZone(circleBall, rectPala);
                    
 //                   private void calculateBallSpeed(int collisionZone) {
 //                       switch(collisionZone) {
 //                           case 0:
 //                               break;
 //                           case 1:
 //                               ballCurrentSpeedX = -3;
 //                               ballCurrentSpeedY = -6;
 //                               break;
 //                           case 2:
 //                               ballCurrentSpeedX = -3;
 //                               ballCurrentSpeedY = -3;
 //                               break;
 //                           case 3:
 //                               ballCurrentSpeedX = -3;
 //                               ballCurrentSpeedY = 3;
 //                               break;
 //                           case 4:
 //                               ballCurrentSpeedX = -3;
 //                               ballCurrentSpeedY = 6;
 //                               break;        
 //                       }
                    //}
//                    calculateBallSpeed(getStickCollisionZone(circleBall, rectPala));
                    
                    // ANIMACIÓN DE LA PALA
                    rectPala.setX(palaPosX);
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




