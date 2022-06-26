package uk.co.mruoc.exercises.naughtsandcrosses.gui;

import lombok.RequiredArgsConstructor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class TurnTakenListener implements ActionListener {

    private final CurrentPlayerPanel currentPlayerPanel;
    private final GameCompleteHandler gameCompleteHandler;

    @Override
    public void actionPerformed(ActionEvent e) {
        GridButton source = (GridButton) e.getSource();
        source.placeToken();
        gameCompleteHandler.displayCompleteIfRequired();
        currentPlayerPanel.updateDisplay();
    }

}
