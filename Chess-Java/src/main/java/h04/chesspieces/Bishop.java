package h04.chesspieces;

import fopbot.Robot;
import fopbot.World;
import h04.movement.DiagonalMover;
import h04.movement.MoveStrategy;
import h04.template.ChessUtils;

import java.awt.Point;


public class Bishop extends Robot implements DiagonalMover {

    private final Team team;

    public Bishop(final int x, final int y, final Team team){
        super(x, y, team == Team.WHITE ? Families.BISHOP_WHITE : Families.BISHOP_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    @Override
    public Team getTeam() { return team;}

    //TODO H4.5
    @Override
    public void moveStrategy(final int dx, final int dy, final MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields() {
        Point[] possibleMoves = getDiagonalMoves(this.getX(), this.getY());
        return possibleMoves;
    }

    @Override
    public Point[] getDiagonalMoves(int dx, int dy) {
        Point[] possibleMoves = {};
        Point currentPoint;
        Team enemyTeam = this.team == Team.WHITE ? Team.BLACK : Team.WHITE;

        // search the points on the right up-side
        for (int cX = dx + 1, cY = dy + 1; cX < World.getWidth() && cY < World.getHeight(); cX++, cY++) {
            currentPoint = new Point(cX, cY);

            if (ChessUtils.isValidCoordinate(currentPoint)) {
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                }
                else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                else
                    break;
            }
        }

        // search the points on the left up-side
        for (int cX = dx - 1, cY = dy + 1; cX >= 0 && cY < World.getHeight(); cX--, cY++) {
            currentPoint = new Point(cX, cY);

            if (ChessUtils.isValidCoordinate(currentPoint)) {
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                }
                else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                else
                    break;
            }
        }

        // search the points on the left down-side
        for (int cX = dx - 1, cY = dy - 1; cX >= 0 && cY >= 0; cX--, cY--) {
            currentPoint = new Point(cX, cY);

            if (ChessUtils.isValidCoordinate(currentPoint)) {
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                }
                else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                else
                    break;
            }
        }

        // search the points on the right down-side
        for (int cX = dx + 1, cY = dy - 1; cX < World.getWidth() && cY >= 0; cX++, cY--) {
            currentPoint = new Point(cX, cY);

            if (ChessUtils.isValidCoordinate(currentPoint)) {
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                }
                else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                else
                    break;
            }
        }

        return possibleMoves;
    }
}
