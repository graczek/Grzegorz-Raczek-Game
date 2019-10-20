package com.graczek.checkers;

import com.graczek.checkers.enums.PawnColor;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Image backImage = new Image("background.jpg");
    private Image blackPawn = new Image("blackpawn.png");
    private Image redPawn = new Image("redpawn.png");

    private final int BOARD_SIZE = 8;
    private final int FIELD_SIZE = 68;
    private final int PAWN_SIZE = 68;

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

        for (int i = 0; i < BOARD_SIZE; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(FIELD_SIZE));
            grid.getRowConstraints().add(new RowConstraints(FIELD_SIZE));
        }

        board.initializeEmptyBoard();
        board.initializeBoardWithStartingPawnSetup();

        reprintBoard(grid, board);

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
        pawn.setFitHeight(PAWN_SIZE);
        pawn.setFitWidth(PAWN_SIZE);
        addClickability(pawn);
        return pawn;
    }

    private void addClickability(ImageView pawn){
        pawn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("Pawn pressed!");
            e.consume();
        });
    }

    public static void main (String[]args){
        launch(args);
    }


}
