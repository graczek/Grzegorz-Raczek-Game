package com.graczek.checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardField extends Rectangle {

    public static final int FIELD_SIZE = 75;

    private Pawn pawn;

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public boolean isEmpty() {
        return pawn == null;
    }

    public BoardField(boolean isDark, int x, int y) {
        setWidth(FIELD_SIZE);
        setHeight(FIELD_SIZE);
        relocate(x * FIELD_SIZE, y * FIELD_SIZE);
        setFill(isDark ? Color.valueOf("#BD995B") : Color.valueOf("#5D3A21"));
    }
}
