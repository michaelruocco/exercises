package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import uk.co.mruoc.exercises.naughtsandcrosses.Game;

import static javax.swing.SwingUtilities.invokeLater;

public class GuiGame implements Game {

    @Override
    public void play() {
        invokeLater(() -> {
            GuiWindow window = new GuiWindow();
            window.display();
        });
    }
}
