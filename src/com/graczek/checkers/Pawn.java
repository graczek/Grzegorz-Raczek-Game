package com.graczek.checkers;

public class Pawn {

    private PawnColor pawnColor;
    private int x;
    private int y;

    public Pawn(PawnColor pawnColor, int x, int y) {
        this.pawnColor = pawnColor;
        this.x = x;
        this.y = y;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "pawnColor=" + pawnColor +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
