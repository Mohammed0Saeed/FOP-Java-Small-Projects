package h04.movement;

import h04.chesspieces.ChessPiece;

import java.awt.Point;

public interface DiagonalMover extends ChessPiece {
    /**
     * get all possible diagonal moves
     * @param dx index in X axis
     * @param dy index in Y axis
     * @return the list of all possible moves
     */
    Point[] getDiagonalMoves(int dx, int dy);
}
