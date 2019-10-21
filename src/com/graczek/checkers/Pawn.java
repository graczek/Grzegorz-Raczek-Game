package com.graczek.checkers;

import com.graczek.checkers.enums.PawnColor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Pawn extends GridPane {

    private double mouseX, mouseY;
    private double oldMouseX, oldMouseY;

    private final int fieldSize = BoardField.FIELD_SIZE;

    private PawnColor pawnColor;

    public Pawn(PawnColor pawnColor, int x, int y) {

        this.pawnColor = pawnColor;

        move(x, y);

        Ellipse bg = new Ellipse(fieldSize * 0.3125, fieldSize * 0.26);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(fieldSize * 0.03);

        bg.setTranslateX((fieldSize - fieldSize * 0.3125 * 2) / 2);
        bg.setTranslateY((fieldSize - fieldSize * 0.26 * 2) / 2 + fieldSize * 0.07);

        Ellipse ellipse = new Ellipse(fieldSize * 0.3125, fieldSize * 0.26);
        ellipse.setFill(pawnColor == PawnColor.LIGHT ? Color.BROWN : Color.DARKOLIVEGREEN);
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(fieldSize * 0.03);

        ellipse.setTranslateX((fieldSize - fieldSize * 0.3125 * 2) / 2);
        ellipse.setTranslateY((fieldSize - fieldSize * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldMouseX, e.getSceneY() - mouseY + oldMouseY);
        });
    }

    public double getOldMouseX() {
        return oldMouseX;
    }

    public double getOldMouseY() {
        return oldMouseY;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void move(int x, int y){
        oldMouseX = x * fieldSize;
        oldMouseY = y * fieldSize;
        relocate(oldMouseX, oldMouseY);
    }

    public void abortMove() {
        relocate(oldMouseX, oldMouseY);
    }

}
