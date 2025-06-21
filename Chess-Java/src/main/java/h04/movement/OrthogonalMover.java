package h04.movement;

import h04.chesspieces.ChessPiece;
import java.awt.Point;

public interface OrthogonalMover extends ChessPiece{
    /**
     * checks all the possible move orthogonally
     * @param dx the x index of the piece
     * @param dy the y index of the piece
     * @return the list of all possible moves
     */
    Point[] getOrthogonalMoves(int dx, int dy);
}
