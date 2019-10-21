package com.graczek.checkers;

import com.graczek.checkers.enums.BoardFieldColor;
import javafx.scene.shape.Rectangle;

public class BoardField extends Rectangle {

    public static final int FIELD_SIZE = 68;

    private BoardFieldColor boardFieldColor;
    private int x;
    private int y;

    private Pawn pawn;

    public BoardField(BoardFieldColor boardFieldColor, int x, int y) {
        setWidth(FIELD_SIZE);
        setHeight(FIELD_SIZE);

        relocate(x * FIELD_SIZE, y * FIELD_SIZE);

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

    public BoardFieldColor getBoardFieldColor() {
        return boardFieldColor;
    }

    public boolean isFieldEmpty(){
        return pawn == null;
    }

    @Override
    public String toString() {
        return "BoardField{" +
                "pawn=" + pawn +
                ", boardFieldColor=" + boardFieldColor +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
