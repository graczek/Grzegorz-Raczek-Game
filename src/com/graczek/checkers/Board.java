package com.graczek.checkers;

public class Board {

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;

    private final BoardField[][] board = new BoardField[BOARD_WIDTH][BOARD_HEIGHT];

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

        Pawn pawn = null;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if( (x + y) % 2 != 0 && y < 3 ) {
                    pawn = new Pawn(PawnColor.BLACK, x, y);
                }
                if( (x + y) % 2 != 0 && y > 5 ) {
                    pawn = new Pawn(PawnColor.RED, x, y);
                }
            }
        }
    }


    public void printBoard(){
        for (int x = 0; x < board.length ; x++) {
            for (int y = 0; y < board.length; y++) {
                System.out.print("[" + board[x][y] + "]");
            }
            System.out.println();
        }
    }


}
