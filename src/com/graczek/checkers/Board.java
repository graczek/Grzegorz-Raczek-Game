package com.graczek.checkers;

public class Board {

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;

    private final BoardField[][] board = new BoardField[BOARD_WIDTH][BOARD_HEIGHT];

    public void initializeEmptyBoard(){
        for (int x = 0; x < board.length ; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = new BoardField();
            }
        }
    }

    public void initializeGameStartBoardSetup(){
        //set pawns on designated fields
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
