package uk.co.mruoc.exercises.instructionprocessing;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultMessageReceiver implements MessageReceiver {

    private final InstructionQueue queue;

    @Override
    public void receive(String message) {

    }

}
