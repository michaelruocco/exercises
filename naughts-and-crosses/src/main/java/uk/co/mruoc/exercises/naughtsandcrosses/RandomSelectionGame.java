package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectRandomLocationStrategy;

public class RandomSelectionGame extends Game {

    public RandomSelectionGame() {
        super(new SelectRandomLocationStrategy());
    }
}
