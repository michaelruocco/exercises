package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.GameState;


import java.util.Optional;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showOptionDialog;

@RequiredArgsConstructor
public class GameCompleteHandler {

    private final GameState state;
    private final GuiWindow window;

    public void displayCompleteIfRequired() {
        if (state.isComplete()) {
            showGameComplete();
        }
    }

    private void showGameComplete() {
        Optional<String> winner = state.getWinner();
        if (winner.isPresent()) {
            showWinner(winner.get());
            return;
        }
        showGameDrawn();
    }

    private void showWinner(String winner) {
        String message = String.format("Player %s wins", winner);
        showGameComplete(message, message);
    }

    private void showGameDrawn() {
        showGameComplete("Game ends in a draw", "Game Drawn");
    }

    private void showGameComplete(String message, String title) {
        Object[] options = {"Close", "Reset"};
        int option = showOptionDialog(window, message, title, YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[1]);
        if (isReset(option)) {
            state.reset();
            window.reset();
            return;
        }
        window.close();
    }

    private static boolean isReset(int option) {
        return option == 1;
    }
}
