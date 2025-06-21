package h04;

import h04.template.ChessUtils;
import h04.template.GameControllerTemplate;
import h04.chesspieces.King;

public class GameController extends GameControllerTemplate {
    public GameController() {
        super();
        setup();
    }

    @Override
    public boolean checkWinCondition() {
        //TODO H4.1
        King[] kings = ChessUtils.getKings();

        for (int i = 0; i < kings.length; i++)
            if(kings[i].isTurnedOff())
                return true;

        return false;
    }
}
