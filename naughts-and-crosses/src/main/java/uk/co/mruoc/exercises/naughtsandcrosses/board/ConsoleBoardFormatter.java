package uk.co.mruoc.exercises.naughtsandcrosses.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleBoardFormatter {

    public String format(Board board) {
        int size = board.getSize() + 1;
        Collection<String> rows = new ArrayList<>();
        rows.add(buildHeader(size));
        for (int y = 1; y < size; y++) {
            Collection<String> tokens = new ArrayList<>();
            tokens.add(Integer.toString(y));
            for (int x = 1; x < size; x++) {
                tokens.add(board.getToken(x, y));
            }
            rows.add(String.join(" ", tokens));
        }
        return rows.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private String buildHeader(int size) {
        String header = IntStream.range(1, size)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        return String.format("  %s", header);
    }
}
