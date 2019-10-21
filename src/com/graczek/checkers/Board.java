package com.graczek.checkers;

import com.graczek.checkers.enums.BoardFieldColor;
import com.graczek.checkers.enums.MoveType;
import com.graczek.checkers.enums.PawnColor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Board {

    public static final int BOARD_SIZE = 8;

    private BoardField[][] board = new BoardField[BOARD_SIZE][BOARD_SIZE];

    public void initializeEmptyBoard(){
        for (int x = 0; x < board.length ; x++) {
            for (int y = 0; y < board.length; y++) {
                if((x + y) % 2 == 0) {
                    board[x][y] = new BoardField(BoardFieldColor.LIGHT, x, y);
                } else {
                    board[x][y] = new BoardField(BoardFieldColor.DARK, x, y);
                }
            }
        }
    }

    public void initializeBoardWithStartingPawnSetup() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                BoardField boardField = board[x][y];
                if(isFieldDark(boardField)) {
                    if(isBoardUpperPart(y)){
                        boardField.setPawn(new Pawn(PawnColor.BLACK, x, y));
                    } else if (isBoardBottomPart(y)) {
                        boardField.setPawn(new Pawn(PawnColor.RED, x, y));
                    }
                }
            }
        }
    }

    public void reprintBoard(GridPane grid, Board board) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Pawn pawn = board.getPawn(x, y);
                if (pawn != null) {
                    ImageView imagePawn = pawn.createPawn(pawn.getPawnColor() == PawnColor.BLACK);
                    grid.add(imagePawn, x, y);
                }
            }
        }
    }

    public Move tryMove(Pawn pawn, int newX, int newY) {
        if(!board[newX][newY].isFieldEmpty() || (newX + newY) %2 == 0 || !isWithinBoardRange(newX, newY)) {
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

    private int translate(double px) {
        return (int)(px + Pawn.PAWN_SIZE / 2) / Pawn.PAWN_SIZE;
    }

    private static boolean isWithinBoardRange(int x, int y) {
        return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
    }

    private boolean isBoardBottomPart(int y){
        return y >= 5;
    }

    private boolean isBoardUpperPart(int y){
        return y <= 2;
    }

    private boolean isFieldDark(BoardField boardField){
        return boardField.getBoardFieldColor() == BoardFieldColor.DARK;
    }

    private void printBoard(){
        for (int x = 0; x < board.length ; x++) {
            for (int y = 0; y < board.length; y++) {
                System.out.print("[" + board[x][y] + "]");
            }
            System.out.println();
        }
    }

    public Pawn getPawn(int x, int y){
        return board[x][y].getPawn();
    }

    public void setPawn(Pawn pawn, int x, int y) {
        board[x][y].setPawn(pawn);
    }

}
