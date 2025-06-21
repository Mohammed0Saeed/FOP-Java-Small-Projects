package h04.chesspieces;

import fopbot.Robot;
//Wichtig für Implementation
import h04.movement.MoveStrategy;
import h04.template.ChessUtils;

import java.awt.Point;

public class King extends Robot implements ChessPiece {

    private final Team team;

    public King(final int x, final int y, final Team team) {
        super(x, y, team == Team.WHITE ? Families.KING_WHITE : Families.KING_BLACK);
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    //TODO H4.4
    @Override
    public void moveStrategy(final int dx, final int dy, final MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields(){
        Point[] possibleMoves = getSurroundingPoints(this.getX(), this.getY());
        return possibleMoves;
    }

    /**
     * Get all the points around the king piece
     * @param x the current location of the king in x
     * @param y the current location of the king in y
     * @return an array of the available points around the king
     */
    public Point[] getSurroundingPoints(int x, int y) {
        // an array to store all the possible points
        Point[] points = {};

        // an array to store the current point in the nested for loop
        Point currentIndex = new Point(x, y);

        // go one step behind to start the iteration in the x-coordination
        for (int i = (int) currentIndex.getX() - 1; i <= currentIndex.getX() + 1; i++) {
            // go one step up to start the iteration in the y-coordination
            for (int j = (int) currentIndex.getY() + 1; j >= currentIndex.getY() - 1; j--) {
                /*
                * add the point to the array only if:
                * 1. the point is not the same as the original point
                * 2. the point is on the board
                * 3. the point is not taken by any other piece except the opponents
                 */
                if (i != currentIndex.getX() && j != currentIndex.getY() && ChessUtils.isValidCoordinate(i, j) && (ChessUtils.getTeamAt(i, j) == null || ChessUtils.getTeamAt(i, j) != this.team)) {
                    points = ChessUtils.push(points, new Point(i, j));
                }
            }
        }

        return points;
    }
}
