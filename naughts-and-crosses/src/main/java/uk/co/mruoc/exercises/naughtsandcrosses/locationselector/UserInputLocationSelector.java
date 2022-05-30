package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import java.util.Scanner;

@RequiredArgsConstructor
public class UserInputLocationSelector implements LocationSelector {

    private final Scanner scanner;

    public UserInputLocationSelector() {
        this(new Scanner(System.in));
    }

    @Override
    public String apply(Board board) {
        System.out.print("input location (as x-y) to place token: ");
        return scanner.next();
    }
}
