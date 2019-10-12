package com.graczek.checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Image backImage = new Image("resources/background.png");
    private Image darkPawn1 = new Image("resources/blackpawn.png");
    private Image darkPawn2 = new Image("resources/blackpawn.png");
    private Image darkPawn3 = new Image("resources/blackpawn.png");
    private Image redPawn = new Image("resources/redpawn.png");
    private FlowPane darkPawns = new FlowPane(Orientation.HORIZONTAL);

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(64, 0, 0, 311));
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setBackground(background);

        ImageView imgDarkPawn1 = new ImageView(darkPawn1);
        imgDarkPawn1.setFitHeight(60);
        imgDarkPawn1.setFitWidth(60);
        ImageView imgDarkPawn2 = new ImageView(darkPawn2);
        imgDarkPawn2.setFitHeight(60);
        imgDarkPawn2.setFitWidth(60);
        ImageView imgDarkPawn3 = new ImageView(darkPawn3);
        imgDarkPawn3.setFitHeight(60);
        imgDarkPawn3.setFitWidth(60);

        darkPawns.getChildren().add(imgDarkPawn1);
        darkPawns.getChildren().add(imgDarkPawn2);
        darkPawns.getChildren().add(imgDarkPawn3);

        grid.add(imgDarkPawn1, 1,0, 1,1);
        grid.add(imgDarkPawn2, 0,1, 1,1);
        grid.add(imgDarkPawn3, 1,2, 1,1);

        Scene scene = new Scene(grid, 1200, 800, Color.TAN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
