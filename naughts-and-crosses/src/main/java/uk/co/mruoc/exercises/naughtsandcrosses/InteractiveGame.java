package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectUserInputLocationStrategy;

public class InteractiveGame extends Game {

    public InteractiveGame() {
        super(new SelectUserInputLocationStrategy());
    }
}
