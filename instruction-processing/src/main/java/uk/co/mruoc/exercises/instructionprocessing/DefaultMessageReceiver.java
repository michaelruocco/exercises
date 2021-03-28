package uk.co.mruoc.exercises.instructionprocessing;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.instructionprocessing.queue.InstructionQueue;

@RequiredArgsConstructor
public class DefaultMessageReceiver implements MessageReceiver {

    private final InstructionQueue queue;
    private final MessageConverter converter;

    public DefaultMessageReceiver(InstructionQueue queue) {
        this(queue, new MessageConverter());
    }

    @Override
    public void receive(String input) {
        InstructionMessage message = converter.toInstructionMessage(input);
        queue.enqueue(message);
    }

}
