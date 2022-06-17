package uk.co.mruoc.exercises.naughtsandcrosses.game;

import uk.co.mruoc.exercises.naughtsandcrosses.board.BoardState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleBoardFormatter {

    public String format(BoardState board) {
        int size = board.getSize() + 1;
        Collection<String> rows = new ArrayList<>();
        rows.add(buildHeader(size));
        for (int y = 1; y < size; y++) {
            rows.add(buildRow(board, size, y));
        }
        return rows.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private static String buildHeader(int size) {
        String header = IntStream.range(1, size)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        return String.format("  %s", header);
    }

    private static String buildRow(BoardState board, int size, int y) {
        Collection<String> tokens = new ArrayList<>();
        tokens.add(Integer.toString(y));
        for (int x = 1; x < size; x++) {
            tokens.add(board.getToken(x, y));
        }
        return String.join(" ", tokens);
    }
}
