package uk.co.mruoc.exercises.instructionprocessing.queue;

import uk.co.mruoc.exercises.instructionprocessing.InstructionMessage;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class InstructionQueue {

    private final AtomicInteger fifoIndex = new AtomicInteger();

    private final Queue<PriorityEntry> messages = new PriorityBlockingQueue<>();

    public void enqueue(InstructionMessage message) {
        add(message);
    }

    public InstructionMessage peek() {
        return tryExtract(messages::peek);
    }

    public InstructionMessage dequeue() {
        return tryExtract(messages::poll);
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

    public int count() {
        return messages.size();
    }

    private void add(InstructionMessage message) {
        messages.add(new PriorityEntry(fifoIndex.getAndIncrement(), message));
    }

    private InstructionMessage tryExtract(Supplier<PriorityEntry> supplier) {
        return Optional.ofNullable(supplier.get())
                .map(PriorityEntry::getMessage)
                .orElseThrow(InstructionQueueEmptyException::new);
    }

}
