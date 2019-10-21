package com.graczek.checkers.enums;

public enum PawnColor {
    RED(-1), BLACK(1);

    public final int movementDirection;

    PawnColor(int movementDirection) {
       this.movementDirection = movementDirection;
    }
}
