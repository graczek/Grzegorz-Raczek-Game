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
//        return MoveType.CAPTURE;
//    }
//
//    private void makeRegularMove(Move move) {
//        movedPawn = this.board.getPawnAt(move.getFrom());
//        this.board.setPawnAt(null, move.getFrom());
//        this.board.setPawnAt(movedPawn, move.getTo());
//    }
//
//    private void checkIfCoordinatesNotTheSame() throws InvalidMoveException {
//        if(from.equals(to)) {
//            throw new InvalidMoveException();
//        }
//    }
//
//    private void checkIfCoordinatesWithingBoardRange() throws InvalidMoveException {
//        if(!isWithinBoardRange(from) || !isWithinBoardRange(to)) {
//            throw new InvalidMoveException();
//        }
//    }
//
//    private boolean isWithinBoardRange(Coordinate coordinate) {
//        return coordinate.getX() >= 0
//                && coordinate.getX() < Board.BOARD_SIZE
//                && coordinate.getY() >= 0
//                && coordinate.getY() < Board.BOARD_SIZE;
//    }
//
//    private Pawn isPawnToBeMovedCorrectColor() throws InvalidMoveException {
//        Pawn pawnToBeMoved = board.getPawnAt(from);
//        if(pawnToBeMoved == null) {
//            throw new InvalidMoveException();
//        } else if(!pawnToBeMoved.getPawnColor().equals(nextMoveColor)) {
//            throw new InvalidMoveException();
//        }
//        return pawnToBeMoved;
//    }
}
