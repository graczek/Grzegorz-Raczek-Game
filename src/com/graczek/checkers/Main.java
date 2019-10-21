package com.graczek.checkers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Image backImage = new Image("background.jpg");

    private Board board = new Board();

    @Override
    public void start(Stage primaryStage) {

        BackgroundSize backgroundSize = new BackgroundSize(1200, 800, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 630, 630, Color.TAN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(BoardField.FIELD_SIZE));
            grid.getRowConstraints().add(new RowConstraints(BoardField.FIELD_SIZE));
        }

        board.initializeEmptyBoard();
        board.initializeBoardWithStartingPawnSetup();
        board.reprintBoard(grid, board);

    }


    public static void main (String[]args){
        launch(args);
    }


}
