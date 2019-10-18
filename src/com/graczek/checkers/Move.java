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

//    public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {
//        //Move move = validateMove(from, to);
//        makeRegularMove(move);
//        return move;
//    }

    private void makeRegularMove(Move move) {
        movedPawn = this.board.getPawnAt(move.getFrom());
        this.board.setPawnAt(null, move.getFrom());
        this.board.setPawnAt(movedPawn, move.getTo());

        //perfromPromotion(move, movedPawn);
    }

//    private Move validateMove(Coordinate from, Coordinate to, Board board, PawnColor nextMoveColor) throws InvalidMoveException {//        if(from.getX() < 0 || from.getX() >= Board.BOARD_SIZE || from.getY() < 0 || from.getY() >= Board.BOARD_SIZE) {
//        nextMoveColor = calculateNextMoveColor();
//        Move move = validator.validate() //tu wroc
//
//        Pawn pawnFrom = board.getPawnAt(from);
//        Pawn pawnTo = board.getPawnAt(to);


        //            throw new InvalidMoveException();
//        }
//        movedPawn = board.getPawnAt(from);
//        if(movedPawn == null){
//            throw new InvalidMoveException();
//        }
//        MoveType moveType = checkAndReturnMoveType();
//
//
//        return new Move(from, to, moveType, board.getPawnAt(from));
//    }
//
//    private MoveType chooseMoveType() throws InvalidMoveException {
//        Pawn pawnToBeMoved = board.getPawnAt(from);
//        Pawn pawnAtDestination = board.getPawnAt(to);
//
//        if(pawnAtDestination == null){
//            return MoveType.NORMAL;
//        }
//        if(pawnAtDestination.getPawnColor().equals(pawnToBeMoved.getPawnColor())) {
//            throw new InvalidMoveException();
//        }
//
//        return MoveType.CAPTURE;
//    }

//    private MoveType checkAndReturnMoveType() throws InvalidMoveException {
//        checkIfCoordinatesNotTheSame();
//        checkIfCoordinatesWithingBoardRange();
//        isPawnToBeMovedCorrectColor();
//        return chooseMoveType();
//    }

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
