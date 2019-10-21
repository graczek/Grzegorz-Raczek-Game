package com.graczek.checkers;

import com.graczek.checkers.enums.BoardFieldColor;
import com.graczek.checkers.enums.MoveType;
import com.graczek.checkers.enums.PawnColor;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Image backImage = new Image("background.jpg");

    public static final int BOARD_SIZE = 8;

    private BoardField[][] board = new BoardField[BOARD_SIZE][BOARD_SIZE];

    private Group boardFields = new Group();
    private Group pawns = new Group();

    private Parent createContent() {

        BackgroundSize backgroundSize = new BackgroundSize(1200, 800, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setPrefSize(BOARD_SIZE * BoardField.FIELD_SIZE, BOARD_SIZE * BoardField.FIELD_SIZE);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        grid.setBackground(background);
        grid.getChildren().addAll(pawns);

        for (int y = 0; y < BOARD_SIZE ; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                BoardField boardField = new BoardField((x + y) % 2 == 0 ? BoardFieldColor.LIGHT : BoardFieldColor.DARK, x, y);
                board[x][y] = boardField;

                boardFields.getChildren().add(boardField);

                Pawn pawn = null;

                if(y <= 2 && (x + y) % 2 != 0) {
                    pawn = makePawn(PawnColor.BLACK, x, y);
                }
                if(y >= 5 && (x + y) % 2 != 0) {
                    pawn = makePawn(PawnColor.RED, x, y);
                }
                if(pawn != null) {
                    boardField.setPawn(pawn);
                    pawns.getChildren().add(pawn);
                }
            }
        }

        return grid;

    }

    public Move tryMove(Pawn pawn, int newX, int newY) {
        if(!board[newX][newY].isFieldEmpty() || (newX + newY) % 2 == 0 || !isWithinBoardRange(newX, newY)) {
            return new Move(MoveType.NONE);
        }

        int x0 = translate(pawn.getOldMouseX());
        int y0 = translate(pawn.getOldMouseY());

        if(Math.abs(newX - x0) == 1 && newY - y0 == pawn.getPawnColor().movementDirection) {
            return new Move(MoveType.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == pawn.getPawnColor().movementDirection *2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if(!board[x1][y1].isFieldEmpty() && board[x1][y1].getPawn().getPawnColor() != pawn.getPawnColor()) {
                return new Move(MoveType.CAPTURE, board[x1][y1].getPawn());
            }
        }

        return new Move(MoveType.NONE);
    }

    private static boolean isWithinBoardRange(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    private Pawn makePawn(PawnColor color, int x, int y) {
        Pawn pawn = new Pawn(color, x, y);

        pawn.setOnMouseReleased(e -> {
            int newX = translate(pawn.getLayoutX());
            int newY = translate(pawn.getLayoutY());

            Move moveResult = tryMove(pawn, newX, newY);

            int x0 = translate(pawn.getOldMouseX());
            int y0 = translate(pawn.getOldMouseY());

            switch(moveResult.getType()) {
                case NONE:
                    pawn.abortMove();
                    break;
                case NORMAL:
                    pawn.move(newX, newY);
                    board[x0][y0].setPawn(null);
                    board[newX][newY].setPawn(pawn);
                    break;
                case CAPTURE:
                    pawn.move(newX, newY);
                    board[x0][y0].setPawn(null);
                    board[newX][newY].setPawn(pawn);

                    Pawn otherPawn = moveResult.getPawn();
                    board[translate(otherPawn.getOldMouseX())][translate(otherPawn.getOldMouseY())].setPawn(null);
                    break;
            }

        });

        return pawn;
    }

    private int translate(double px) {
        return (int)(px + Pawn.PAWN_SIZE / 2) / Pawn.PAWN_SIZE;
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent());

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();




//        for (int i = 0; i < Board.BOARD_SIZE; i++) {
//            grid.getColumnConstraints().add(new ColumnConstraints(BoardField.FIELD_SIZE));
//            grid.getRowConstraints().add(new RowConstraints(BoardField.FIELD_SIZE));
//        }
//
//        board.initializeEmptyBoard();
//        board.initializeBoardWithStartingPawnSetup();
        //board.reprintBoard(grid, board);

    }


    public static void main (String[]args){
        launch(args);
    }


}
