package com.graczek.checkers.exceptions;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("Invalid move!");
    }
}
