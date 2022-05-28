package uk.co.mruoc.exercises.naughtsandcrosses.board;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class SelectUserInputLocationStrategy implements SelectLocationStrategy {

    private final Scanner scanner;

    public SelectUserInputLocationStrategy() {
        this(new Scanner(System.in));
    }

    @Override
    public Location apply(Board board) {
        System.out.println("input location to place key");
        String key = scanner.next();
        return board.getLocation(key);
    }
}
