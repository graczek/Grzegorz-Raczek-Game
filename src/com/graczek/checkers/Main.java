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

    private Image backImage = new Image("resources/background2.jpg");
    private Image blackPawn = new Image("resources/blackpawn.png");
    private Image blackPawn1 = new Image("resources/blackpawn.png");
    private Image blackPawn2 = new Image("resources/blackpawn.png");
    private Image blackPawn3 = new Image("resources/blackpawn.png");
    private Image blackPawn4 = new Image("resources/blackpawn.png");
    private Image redPawn = new Image("resources/redpawn.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(46, 0, 0, 295));

        grid.setHgap(0);
        grid.setVgap(0);
        grid.setBackground(background);

        ImageView imgBlackPawn = new ImageView(blackPawn);
        imgBlackPawn.setFitHeight(76);
        imgBlackPawn.setFitWidth(76);

        ImageView imgBlackPawn1 = new ImageView(blackPawn1);
        imgBlackPawn1.setFitHeight(76);
        imgBlackPawn1.setFitWidth(76);

        ImageView imgBlackPawn2 = new ImageView(blackPawn2);
        imgBlackPawn2.setFitHeight(76);
        imgBlackPawn2.setFitWidth(76);

        ImageView imgBlackPawn3 = new ImageView(blackPawn3);
        imgBlackPawn3.setFitHeight(76);
        imgBlackPawn3.setFitWidth(76);

        ImageView imgBlackPawn4 = new ImageView(blackPawn4);
        imgBlackPawn4.setFitHeight(76);
        imgBlackPawn4.setFitWidth(76);

        ImageView imgRedPawn = new ImageView(redPawn);
        imgRedPawn.setFitWidth(76);
        imgRedPawn.setFitHeight(76);

        grid.getChildren().addAll(imgBlackPawn, imgBlackPawn1, imgBlackPawn2, imgBlackPawn3, imgBlackPawn4);

        GridPane.setConstraints(imgBlackPawn, 1, 0);
        GridPane.setConstraints(imgBlackPawn1, 3, 1);
        GridPane.setConstraints(imgBlackPawn2, 5, 2);
        GridPane.setConstraints(imgBlackPawn3, 7, 3);
        GridPane.setConstraints(imgBlackPawn4, 9, 4);


        Scene scene = new Scene(grid, 1200, 800, Color.TAN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        Board board = new Board();
        board.initializeEmptyBoard();
        board.printBoard();

    }
}
