package com.graczek.checkers;

import com.graczek.checkers.enums.BoardFieldColor;
import com.graczek.checkers.enums.PawnColor;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //stores size of the checkers board
    public static final int BOARD_SIZE = 8;

    //stores fields which form the board
    private BoardField[][] board = new BoardField[BOARD_SIZE][BOARD_SIZE];

    private List<Move> moveHistory = new ArrayList<>();

    public BoardField[][] getBoard() {
        return board;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }

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

    //gets pawn from board based on given coordinates
    public Pawn getPawnAt(Coordinate coordinate) {
        BoardField boardField = board[coordinate.getX()][coordinate.getY()];
        return boardField.getPawn();
    }

    //sets pawn on board based on given coordinates
    public void setPawnAt(Pawn pawn, Coordinate coordinate) {
        BoardField boardField = board[coordinate.getX()][coordinate.getY()];
        boardField.setPawn(pawn);
    }

    public Pawn getPawn(int x, int y){
        return board[x][y].getPawn();
    }

}
