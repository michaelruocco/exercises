package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import uk.co.mruoc.exercises.naughtsandcrosses.GameState;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import static java.awt.event.WindowEvent.WINDOW_CLOSING;

public class GuiWindow extends JFrame {

    private final GridPanel gridPanel;

    public GuiWindow() {
        this(new GameState());
    }

    public GuiWindow(GameState state) {
        super("Naughts & Crosses");

        CurrentPlayerPanel currentPlayerPanel = new CurrentPlayerPanel(state);
        this.gridPanel = new GridPanel(state);

        gridPanel.addButtonActionListener(new TurnTakenListener(currentPlayerPanel, new GameCompleteHandler(state, this)));

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(gridPanel, BorderLayout.CENTER);
        container.add(currentPlayerPanel, BorderLayout.PAGE_END);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(container);
    }

    public void reset() {
        gridPanel.reset();
    }

    public void display() {
        pack();
        setVisible(true);
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WINDOW_CLOSING));
    }
}
