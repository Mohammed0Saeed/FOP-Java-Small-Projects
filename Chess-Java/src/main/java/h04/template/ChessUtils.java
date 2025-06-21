package h04.template;

import fopbot.World;
import h04.chesspieces.ChessPiece;
import h04.chesspieces.King;
import h04.chesspieces.Team;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.awt.Point;
import java.util.Optional;
import java.util.function.Predicate;

public class ChessUtils {

    public static @Nullable ChessPiece getPieceAt(final int x, final int y) {
        return World.getGlobalWorld().getField(x, y).getEntities()
            .stream()
            .filter(ChessPiece.class::isInstance)
            .map(ChessPiece.class::cast)
            .filter(Predicate.not(ChessPiece::isTurnedOff))
            .findFirst()
            .orElse(null);
    }

    public static @Nullable ChessPiece getPieceAt(final Point p) {
        return getPieceAt(p.x, p.y);
    }

    public static King[] getKings() {
        return World.getGlobalWorld().getAllFieldEntities()
            .stream()
            .filter(King.class::isInstance)
            .map(King.class::cast)
            .toArray(King[]::new);
    }

    /**
     * Returns the team at the given position, or {@code null} if there is no team.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the team at the given position, or {@code null} if there is no team
     */
    public static @Nullable Team getTeamAt(final int x, final int y) {
        return Optional.ofNullable(getPieceAt(x, y))
            .map(ChessPiece::getTeam)
            .orElse(null);
    }

    public static @Nullable Team getTeamAt(final Point p) {
        return getTeamAt(p.x, p.y);
    }

    public static boolean isValidCoordinate(final int x, final int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public static boolean isValidCoordinate(final Point p) {
        return isValidCoordinate(p.x, p.y);
    }

    public static void setFieldColor(final Point field, final @Nullable Color c) {
        World.getGlobalWorld().getField(field.x, field.y).setFieldColor(c);
    }

    public static void colorMoveFields(final Point... fields) {
        for (final Point field : fields) {
            if (field == null)
                continue;
            setFieldColor(field, getPieceAt(field) != null ? Color.RED : Color.GREEN);
        }
    }

    public static void resetFieldColor() {
        for (int y = 0; y < World.getHeight(); y++) {
            for (int x = 0; x < World.getWidth(); x++) {
                setFieldColor(new Point(x, y), null);
            }
        }
    }

    public static Point[] push(Point[] moves, int x, int y){
        Point[] newPointsArray = new Point[moves.length + 1];

        for (int i = 0; i < moves.length; i++)
            newPointsArray[i] = moves[i];

        newPointsArray[newPointsArray.length - 1] = new Point(x, y);

        return newPointsArray;
    }

    /**
     * adding a point to the moves array
     * @param moves the array where we have to add the element
     * @param point the point that must be added
     * @return an array with all the points including the new point
     */
    public static Point[] push(Point[] moves, Point point){
        Point[] newPointsArray = new Point[moves.length + 1];

        for (int i = 0; i < moves.length; i++)
            newPointsArray[i] = moves[i];

        newPointsArray[newPointsArray.length - 1] = point;

        return newPointsArray;
    }

    public static Point[] union(Point[] moves1, Point[] moves2){
        Point[] newPointsArray = new Point[moves1.length + moves2.length];

        for(int i = 0; i < newPointsArray.length; i++) {
            if (i < moves1.length)
                newPointsArray[i] = moves1[i];
            else
                newPointsArray[i] = moves2[i - moves1.length];
        }

        return newPointsArray;
    }
}
