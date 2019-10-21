package com.graczek.checkers;

import com.graczek.checkers.enums.PawnColor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Pawn extends GridPane {

    private double mouseX, mouseY;
    private double oldMouseX, oldMouseY;

    public static final int PAWN_SIZE = 61;

    private PawnColor pawnColor;

    public Pawn(PawnColor pawnColor, int x, int y) {

        this.pawnColor = pawnColor;

        move(x, y);

        Ellipse bg = new Ellipse(PAWN_SIZE * 0.3125, PAWN_SIZE * 0.26);
        bg.setFill(Color.GRAY);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(PAWN_SIZE * 0.03);

        bg.setTranslateX((PAWN_SIZE - PAWN_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((PAWN_SIZE - PAWN_SIZE * 0.26 * 2) / 2 + PAWN_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(PAWN_SIZE * 0.3125, PAWN_SIZE * 0.26);
        ellipse.setFill(pawnColor == PawnColor.RED ? Color.RED : Color.BLACK);
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(PAWN_SIZE * 0.03);

        ellipse.setTranslateX((PAWN_SIZE - PAWN_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((PAWN_SIZE - PAWN_SIZE * 0.26 * 2) / 2);

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
        oldMouseX = x * PAWN_SIZE;
        oldMouseY = y * PAWN_SIZE;
        relocate(oldMouseX, oldMouseY);
    }

    public void abortMove() {
        relocate(oldMouseX, oldMouseY);
    }

}
