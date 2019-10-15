package com.graczek.checkers;

public class Board {

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;

    private Pawn pawn;

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

    public void printBoard(){
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

}
