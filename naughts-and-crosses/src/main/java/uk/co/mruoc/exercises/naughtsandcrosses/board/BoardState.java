package uk.co.mruoc.exercises.naughtsandcrosses.board;

import java.util.stream.Stream;

public interface BoardState {

    int getSize();

    String getToken(int x, int y);

    String getToken(String locationKey);

    Stream<String> getLocationKeys();
}
