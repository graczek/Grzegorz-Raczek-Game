package com.graczek.checkers;

import com.graczek.checkers.enums.MoveType;

public class Move {

    private MoveType type;
    private Pawn pawn;

    public MoveType getType() {
        return type;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public Move(MoveType type, Pawn pawn) {
        this.type = type;
        this.pawn = pawn;
    }

    public Move(MoveType type) {
        this(type, null);
    }
}
