package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import uk.co.mruoc.exercises.naughtsandcrosses.GameState;
import uk.co.mruoc.exercises.naughtsandcrosses.board.BoardState;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.stream.Collectors;

public class GridPanel extends JPanel {

    private final Collection<GridButton> buttons;

    public GridPanel(GameState game) {
        BoardState board = game.getBoardState();
        int size = board.getSize();
        setLayout(new GridLayout(size, size));
        this.buttons = buildButtons(game, board);
        this.buttons.forEach(this::add);
    }

    public void reset() {
        buttons.forEach(GridButton::updateDisplay);
    }

    public void addButtonActionListener(ActionListener listener) {
        this.buttons.forEach(button -> button.addActionListener(listener));
    }

    private Collection<GridButton> buildButtons(GameState game, BoardState board) {
        return board.getLocationKeys()
                .map(location -> new GridButton(game, location))
                .collect(Collectors.toList());
    }
}
