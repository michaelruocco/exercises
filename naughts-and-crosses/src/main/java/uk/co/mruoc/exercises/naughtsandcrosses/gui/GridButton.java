package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import uk.co.mruoc.exercises.naughtsandcrosses.GameState;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

public class GridButton extends JButton {

    private final transient GameState state;
    private final String location;

    public GridButton(GameState state, String location) {
        this(state, location, new GridFont(), new Dimension(120, 100));
    }

    public GridButton(GameState state, String location, Font font, Dimension preferredSize) {
        this.state = state;
        this.location = location;
        setFont(font);
        setPreferredSize(preferredSize);
        updateDisplay();
    }

    public void placeToken() {
        state.placeToken(location);
        updateDisplay();
    }

    public void updateDisplay() {
        setText(state.getTokenAt(location));
        repaint();
    }
}
