package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class SpecificLocationSelector implements LocationSelector {

    private final List<String> keys;

    public SpecificLocationSelector(String... keys) {
        this(new ArrayList<>(Arrays.asList(keys)));
    }

    @Override
    public String apply(Board board) {
        if (keys.isEmpty()) {
            throw new NoKeysRemainingException();
        }
        return keys.remove(0);
    }
}
