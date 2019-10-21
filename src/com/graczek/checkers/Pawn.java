package com.graczek.checkers;

import com.graczek.checkers.enums.PawnColor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Pawn extends GridPane {

    private Image blackPawn = new Image("blackpawn.png");
    private Image redPawn = new Image("redpawn.png");

    private double mouseX, mouseY;
    private double oldMouseX, oldMouseY;

    public static final int PAWN_SIZE = 68;

    private PawnColor pawnColor;
    private int x;
    private int y;

    public Pawn(PawnColor pawnColor, int x, int y) {
        this.pawnColor = pawnColor;
        this.x = x;
        this.y = y;

        move(x, y);
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

    public ImageView createPawn (boolean isDark) {
        Image pawnImage = isDark ? blackPawn : redPawn;
        ImageView pawn = new ImageView(pawnImage);
        pawn.setFitHeight(PAWN_SIZE);
        pawn.setFitWidth(PAWN_SIZE);
        addClickability(pawn);
        return pawn;
    }

    private void addClickability(ImageView pawn){
        pawn.setOnMousePressed(e -> {
            oldMouseX = e.getSceneX();
            oldMouseY = e.getSceneY();
        });

        pawn.setOnMouseDragged(e -> {
            pawn.setTranslateX(e.getSceneX() - oldMouseX);
            pawn.setTranslateY(e.getSceneY() - oldMouseY);
        });

//        pawn.setOnMouseReleased(e -> {
//            mouseX = e.getSceneX();
//            mouseY = e.getSceneY();
//        });
    }

    public void move(int x, int y){
        oldMouseX = x * PAWN_SIZE;
        oldMouseY = y * PAWN_SIZE;
        relocate(oldMouseX, oldMouseY);
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
