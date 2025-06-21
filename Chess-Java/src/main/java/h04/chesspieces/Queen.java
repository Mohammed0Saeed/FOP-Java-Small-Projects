package h04.chesspieces;

import fopbot.Robot;
//Wichtig für Implementation
import h04.movement.MoveStrategy;
import fopbot.World;
import h04.movement.DiagonalMover;
import h04.movement.OrthogonalMover;
import h04.template.ChessUtils;

import java.awt.Point;

public class Queen extends Robot implements OrthogonalMover, DiagonalMover {
    private final Team team;

    public Queen(final int x, final int y, final Team team) {
        super(x, y, team == Team.WHITE ? Families.QUEEN_WHITE : Families.QUEEN_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    @Override
    public Team getTeam() {
        return team;
    }

    //TODO H4.6
    @Override
    public void moveStrategy(final int dx, final int dy, final MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields() {
        Point[] possibleDiagonalMoves = getDiagonalMoves(this.getX(), this.getY());
        Point[] possibleOrthogonalMoves = getOrthogonalMoves(this.getX(), this.getY());

        Point[] allPossibleMoves = ChessUtils.union(possibleDiagonalMoves, possibleOrthogonalMoves);

        return allPossibleMoves;
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

    @Override
    public Point[] getOrthogonalMoves(int dx, int dy) {
        // create an array to store all the possible moves
        Point[] possibleMoves = {};
        Point currentPoint;
        Team enemyTeam = this.team == Team.WHITE ? Team.BLACK : Team.WHITE;

        // get the available points in front of the chess piece
        for (int cY = dy + 1; cY < World.getHeight(); cY++) {
            // create a local point to validate it
            currentPoint = new Point(dx, cY);

            // check if the point is in the board and does not have the same team as the piece itself
            if (ChessUtils.isValidCoordinate(currentPoint)) {
                // check if the point has the opponent team
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                } else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);

                else
                    break;
            }
        }

        // get the available points behind the chess piece
        for (int cY = dy - 1; cY >= 0; cY--) {
            // create a local point to validate it
            currentPoint = new Point(dx, cY);

            // check if the point is in the board and does not have the same team as the piece itself
            if (ChessUtils.isValidCoordinate(currentPoint)) {
                // check if the point has the opponent team
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                } else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);

                else
                    break;
            }
        }

        // get the available points in the right of the chess piece
        for (int cX = dx + 1; cX < World.getWidth(); cX++) {
            // create a local point to validate it
            currentPoint = new Point(cX, dy);

            // check if the point is in the board and does not have the same team as the piece itself
            if (ChessUtils.isValidCoordinate(currentPoint)) {
                // check if the point has the opponent team
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                } else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);

                else
                    break;
            }
        }

        // get the available points behind the chess piece
        for (int cX = dx - 1; cX >= 0; cX--) {
            // create a local point to validate it
            currentPoint = new Point(cX, dy);

            // check if the point is in the board and does not have the same team as the piece itself
            if (ChessUtils.isValidCoordinate(currentPoint)) {
                // check if the point has the opponent team
                if (ChessUtils.getTeamAt(currentPoint) == enemyTeam) {
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);
                    break;
                } else if (ChessUtils.getTeamAt(currentPoint) == null)
                    possibleMoves = ChessUtils.push(possibleMoves, currentPoint);

                else
                    break;
            }
        }
        return possibleMoves;
    }
}
