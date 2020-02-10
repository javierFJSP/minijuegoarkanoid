package com.mycompany.arkanoid;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
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
    final short TEXT_SIZE = 24;
    
    short ballCenterX = 0;
    byte ballCurrentSpeedX = 3;
    byte ballDirectionX = 1;
    
    short ballCenterY = 30;
    byte ballCurrentSpeedY = 3;
    byte ballDirectionY = 1;
    
    short palaPosX = (short)((Scene_height)/2);
    short palaPosY = 380;
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
        
        //ladrillos
        Rectangle rectbrick1 = new Rectangle();
        rectbrick1.setWidth(50);
        rectbrick1.setHeight(10);
        rectbrick1.setX(100);
        rectbrick1.setY(50);
        rectbrick1.setFill(Color.YELLOW);
        root.getChildren().add(rectbrick1);
        
        Rectangle rectbrick2 = new Rectangle();
        rectbrick2.setWidth(50);
        rectbrick2.setHeight(10);
        rectbrick2.setX(200);
        rectbrick2.setY(50);
        rectbrick2.setFill(Color.PINK);
        root.getChildren().add(rectbrick2);
        
        //barra de control
   //     RECTANGLE  =;
//        Image barra = new Image(getClass().getResourceAsStream("/imagenes/barracontrol.PNG"));
//        ImageView imageViewPala = new ImageView(barra);
//        imageViewPala.setX(palaPosX);
//        imageViewPala.setY(palaPosY);
//        root.getChildren().add(imageViewPala);
        
    // Barra de control
        Rectangle rectPala = new Rectangle();
        rectPala.setWidth(50);
        rectPala.setHeight(10);
        rectPala.setX(palaPosX);
        rectPala.setY(palaPosY);
        rectPala.setFill(Color.GREEN);        
        root.getChildren().add(rectPala);
        
    // Panel para mostrar textos (puntuaciones)
        HBox paneTextScore = new HBox();
        paneTextScore.setTranslateY(20);
        paneTextScore.setMinWidth(Scene_width);
        paneTextScore.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextScore);
        
    // Texto de etiqueta para la puntuación
        Text textTitleScore = new Text("Score: ");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        
    // Texto para la puntuación
        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        
    // Texto de etiqueta para la puntuación máxima
        Text textTitleMaxScore = new Text("          Max.Score: ");
        textTitleMaxScore.setFont(Font.font(TEXT_SIZE));
        textTitleMaxScore.setFill(Color.WHITE);
        
    // Texto para la puntuación máxima
        textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.WHITE);

    // Añadir los textos al panel reservado para ellos 
        paneTextScore.setSpacing(10);
        paneTextScore.getChildren().add(textTitleScore);
        paneTextScore.getChildren().add(textScore);
        paneTextScore.getChildren().add(textTitleMaxScore);
        paneTextScore.getChildren().add(textHighScore);
        
        
        
        

 
    
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
                        if(score > highScore) {
                            highScore = score;
                            textHighScore.setText(String.valueOf(highScore));
                        }
                        score = 0;
                        textScore.setText(String.valueOf(score));
                        ballDirectionY = -1;
                    } else if(ballCenterY <= 0){
                        ballDirectionY = 1;
                    }
                    
                // DETECCIÓN DE COLISIÓN BARRA DE CONTROL Y BOLA
                    Shape shapeCollision = Shape.intersect(circleBall, rectPala);
                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
                    if(colisionVacia == false && ballDirectionX == 1) {
                        ballDirectionY = -1;
                    } else if(colisionVacia == false && ballDirectionX == -1) {
                        ballDirectionY = 1;
                    }
                    
                    //DETECCION DE COLISION BOLA Y LADRILLO 1
                    Shape shapeCollision1 = Shape.intersect(circleBall, rectbrick1);
                    boolean colisionVacia1 = shapeCollision1.getBoundsInLocal().isEmpty();
                    if(colisionVacia1 == false) {
                        rectbrick1.setVisible(colisionVacia1 == true); 
                        score++;
                        textScore.setText(String.valueOf(score));
                    }
                    
                    //DETECCION DE COLISION BOLA Y LADRILLO 2
                    Shape shapeCollision2 = Shape.intersect(circleBall, rectbrick2);
                    boolean colisionVacia2 = shapeCollision2.getBoundsInLocal().isEmpty();
                    if(colisionVacia2 == false) {
                        rectbrick2.setVisible(colisionVacia2 == true); 
                        score++;
                        textScore.setText(String.valueOf(score));
                    }                  
                    
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




