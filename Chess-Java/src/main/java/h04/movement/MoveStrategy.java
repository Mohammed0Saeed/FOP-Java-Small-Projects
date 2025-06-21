package h04.movement;

import fopbot.Robot;

public interface MoveStrategy {
    /**
     * a method to implement the movement of chess pieces
     */

    void move(Robot robot, int dx, int dy);
}
