package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Location;
import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectLocationStrategy;
import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectUserInputLocationStrategy;

public class InteractiveGame extends Game {

    public InteractiveGame() {
        super(new SelectUserInputLocationStrategy());
    }
}
