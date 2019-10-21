package com.graczek.checkers;

import com.graczek.checkers.enums.BoardFieldColor;
import javafx.scene.shape.Rectangle;

public class BoardField extends Rectangle {

    private Pawn pawn;

    public static final int FIELD_SIZE = 68;

    private BoardFieldColor boardFieldColor;
    private int x;
    private int y;

    public BoardField(BoardFieldColor boardFieldColor, int x, int y) {
        setWidth(FIELD_SIZE);
        setHeight(FIELD_SIZE);

        relocate(x * FIELD_SIZE, y * FIELD_SIZE);

        this.boardFieldColor = boardFieldColor;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public BoardFieldColor getBoardFieldColor() {
        return boardFieldColor;
    }

    public boolean isFieldEmpty(){
        return pawn == null;
    }

}
