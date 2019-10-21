package com.graczek.checkers.enums;

public enum PawnColor {
    LIGHT(-1), DARK(1);

    public final int movementDirection;

    PawnColor(int movementDirection) {
       this.movementDirection = movementDirection;
    }
}
