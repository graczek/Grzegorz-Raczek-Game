package com.graczek.checkers;

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

    private final int fieldSize = BoardField.FIELD_SIZE;

    private Group boardFields = new Group();
    private Group pawns = new Group();

    private Parent createContent() {

        BackgroundSize backgroundSize = new BackgroundSize(1200, 800, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setPrefSize(BOARD_SIZE * fieldSize, BOARD_SIZE * fieldSize);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        grid.setBackground(background);
        grid.getChildren().addAll(boardFields, pawns);

        for (int y = 0; y < BOARD_SIZE ; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                BoardField boardField = new BoardField((x + y) % 2 == 0,  x, y);
                board[x][y] = boardField;

                boardFields.getChildren().add(boardField);

                Pawn pawn = null;

                if(isBoardUpperPart(y) && isFieldDark(x, y)) {
                    pawn = makePawn(PawnColor.DARK, x, y);
                }
                if(isBoardBottomPart(y) && isFieldDark(x, y)) {
                    pawn = makePawn(PawnColor.LIGHT, x, y);
                }
                if(pawn != null) {
                    boardField.setPawn(pawn);
                    pawns.getChildren().add(pawn);
                }
            }
        }

        return grid;

    }

    private boolean isBoardBottomPart(int y){
        return y >= 5;
    }

    private boolean isBoardUpperPart(int y){
        return y <= 2;
    }

    private boolean isFieldDark(int x, int y){
        return (x + y) % 2 != 0;
    }

    public Move tryMove(Pawn pawn, int newX, int newY) {
        if(!board[newX][newY].isEmpty() || (newX + newY) % 2 == 0 || !isWithinBoardRange(newX, newY)) {
            return new Move(MoveType.NONE);
        }

        int x0 = translate(pawn.getOldMouseX());
        int y0 = translate(pawn.getOldMouseY());

        if(Math.abs(newX - x0) == 1 && newY - y0 == pawn.getPawnColor().movementDirection) {
            return new Move(MoveType.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == pawn.getPawnColor().movementDirection *2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if(!board[x1][y1].isEmpty() && board[x1][y1].getPawn().getPawnColor() != pawn.getPawnColor()) {
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

            Move moveResult;

            if(newX < 0 || newY < 0 || newX >= BOARD_SIZE || newY >= BOARD_SIZE) {
                moveResult = new Move(MoveType.NONE);
            } else {
                moveResult = tryMove(pawn, newX, newY);
            }

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
                    pawns.getChildren().remove(otherPawn);
                    break;
            }

        });

        return pawn;
    }

    private int translate(double px) {
        return (int)(px + fieldSize / 2) / fieldSize;
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent());

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main (String[]args){
        launch(args);
    }


}
