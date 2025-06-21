package h04.movement;

import fopbot.Robot;

public class TeleportingMoveStrategy implements MoveStrategy{
    @Override
    public void move(final Robot robot, final int dx, final int dy) {
        robot.setField(robot.getX() + dx, robot.getY() + dy);
    }
}
