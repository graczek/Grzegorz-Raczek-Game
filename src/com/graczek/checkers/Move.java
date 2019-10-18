package com.graczek.checkers;

import com.graczek.checkers.enums.MoveType;
import com.graczek.checkers.enums.PawnColor;
import com.graczek.checkers.exceptions.InvalidMoveException;

public class Move {
    private Coordinate from;
    private Coordinate to;
    private MoveType moveType;
    private Pawn movedPawn;
    private Board board;
    private PawnColor nextMoveColor;

    public Move(Coordinate from, Coordinate to, MoveType moveType, Pawn movedPawn) {
        this.from = from;
        this.to = to;
        this.moveType = moveType;
        this.movedPawn = movedPawn;
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public void setTo(Coordinate to) {
        this.to = to;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public Pawn getMovedPawn() {
        return movedPawn;
    }

    public void setMovedPawn(Pawn movedPawn) {
        this.movedPawn = movedPawn;
    }

    private MoveType chooseMoveType() throws InvalidMoveException {
        Pawn pawnToBeMoved = board.getPawnAt(from);
        Pawn pawnAtDestination = board.getPawnAt(to);

        if(pawnAtDestination == null){
            return MoveType.NORMAL;
        }
        if(pawnAtDestination.getPawnColor().equals(pawnToBeMoved.getPawnColor())) {
            throw new InvalidMoveException();
        }
        return MoveType.CAPTURE;
    }

    private void makeRegularMove(Move move) {
        movedPawn = this.board.getPawnAt(move.getFrom());
        this.board.setPawnAt(null, move.getFrom());
        this.board.setPawnAt(movedPawn, move.getTo());
    }

    private void checkIfCoordinatesNotTheSame() throws InvalidMoveException {
        if(from.equals(to)) {
            throw new InvalidMoveException();
        }
    }

    private void checkIfCoordinatesWithingBoardRange() throws InvalidMoveException {
        if(!isWithinBoardRange(from) || !isWithinBoardRange(to)) {
            throw new InvalidMoveException();
        }
    }

    private boolean isWithinBoardRange(Coordinate coordinate) {
        return coordinate.getX() >= 0
                && coordinate.getX() < Board.BOARD_SIZE
                && coordinate.getY() >= 0
                && coordinate.getY() < Board.BOARD_SIZE;
    }

    private Pawn isPawnToBeMovedCorrectColor() throws InvalidMoveException {
        Pawn pawnToBeMoved = board.getPawnAt(from);
        if(pawnToBeMoved == null) {
            throw new InvalidMoveException();
        } else if(!pawnToBeMoved.getPawnColor().equals(nextMoveColor)) {
            throw new InvalidMoveException();
        }
        return pawnToBeMoved;
    }
}
