package uk.co.mruoc.exercises.instructionprocessing;

import java.util.LinkedList;
import java.util.Queue;

public class InstructionQueue {

    private final Queue<InstructionMessage> messages = new LinkedList<>();

    public void enqueue(InstructionMessage message) {
        messages.add(message);
    }

    public InstructionMessage peek() {
        return messages.peek();
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

}
