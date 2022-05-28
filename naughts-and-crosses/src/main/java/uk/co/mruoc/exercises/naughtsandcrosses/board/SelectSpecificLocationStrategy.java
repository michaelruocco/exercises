package uk.co.mruoc.exercises.naughtsandcrosses.board;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class SelectSpecificLocationStrategy implements SelectLocationStrategy {

    private final List<String> keys;

    public SelectSpecificLocationStrategy(String... keys) {
        this(new ArrayList<>(Arrays.asList(keys)));
    }

    @Override
    public Location apply(Board board) {
        return board.getLocation(keys.remove(0));
    }
}
