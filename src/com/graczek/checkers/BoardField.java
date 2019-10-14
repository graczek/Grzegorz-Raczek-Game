package com.graczek.checkers;

public class BoardField {

    private Pawn pawn;

    private BoardFieldColor boardFieldColor;
    private int x;
    private int y;

    public BoardField(BoardFieldColor boardFieldColor, int x, int y) {
        this.boardFieldColor = boardFieldColor;
        this.x = x;
        this.y = y;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public boolean isFieldEmpty(){
        return pawn == null;
    }
}
