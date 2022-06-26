package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import uk.co.mruoc.exercises.naughtsandcrosses.GameState;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class CurrentPlayerPanel extends JPanel {

    private final transient GameState state;
    private final JLabel playerLabel;

    public CurrentPlayerPanel(GameState state) {
        this(state, new CurrentPlayerFont());
    }

    public CurrentPlayerPanel(GameState state, Font font) {
        this(state, createPlayerLabel(state, font), font);
    }

    public CurrentPlayerPanel(GameState state, JLabel playerLabel, Font font) {
        this.state = state;
        this.playerLabel = playerLabel;
        add(createLabel(font));
        add(playerLabel);
    }

    public void updateDisplay() {
        playerLabel.setText(state.getCurrentPlayer());
        repaint();
    }

    private static JLabel createLabel(Font font) {
        JLabel label = new JLabel();
        label.setText("Current Player: ");
        label.setFont(font);
        return label;
    }

    private static JLabel createPlayerLabel(GameState state, Font font) {
        JLabel label = new JLabel();
        label.setText(state.getCurrentPlayer());
        label.setFont(font.deriveFont(Font.BOLD));
        return label;
    }
}
