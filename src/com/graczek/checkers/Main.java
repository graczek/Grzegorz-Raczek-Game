package com.graczek.checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Image backImage = new Image("background.jpg");
    private Image blackPawn = new Image("blackpawn.png");
    private Image redPawn = new Image("redpawn.png");

    private final int BOARD_SIZE = 8;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(730, 730, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);

        grid.setBackground(background);

        for (int i = 0; i < BOARD_SIZE; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(68));
            grid.getRowConstraints().add(new RowConstraints(68));
        }

        Board board = new Board();
        board.initializeEmptyBoard();
        board.initializeBoardWithStartingPawnSetup();

        reprintBoard(grid, board);

        Scene scene = new Scene(grid, 630, 630, Color.TAN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void reprintBoard(GridPane grid, Board board) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Pawn pawn = board.getPawn(x, y);
                if (pawn != null) {
                    ImageView imagePawn = createPawn(pawn.getPawnColor() == PawnColor.BLACK);
                    grid.add(imagePawn, x, y);
                }
            }
        }
    }

    private ImageView createPawn (boolean isDark) {
        Image pawnImage = isDark ? blackPawn : redPawn;
        ImageView pawn = new ImageView(pawnImage);
        pawn.setFitHeight(68);
        pawn.setFitWidth(68);
        return pawn;
    }


    public static void main (String[]args){
        launch(args);
    }
}
