package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificLocationSelector implements LocationSelector {

    private final List<String> keys;

    public SpecificLocationSelector(String... keys) {
        this(new ArrayList<>(Arrays.asList(keys)));
    }

    public SpecificLocationSelector(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public String apply(Board board) {
        if (keys.isEmpty()) {
            throw new NoKeysRemainingException();
        }
        return keys.remove(0);
    }
}
