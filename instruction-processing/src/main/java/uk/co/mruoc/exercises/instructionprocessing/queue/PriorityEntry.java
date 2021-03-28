package uk.co.mruoc.exercises.instructionprocessing.queue;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.instructionprocessing.InstructionMessage;

@RequiredArgsConstructor
@Data
public class PriorityEntry implements Comparable<PriorityEntry> {

    private final int fifoIndex;
    private final InstructionMessage message;
    private final Priority priority;

    public PriorityEntry(int fifoIndex, InstructionMessage message) {
        this.fifoIndex = fifoIndex;
        this.message = message;
        this.priority = calculatePriority(message);
    }

    @Override
    public int compareTo(PriorityEntry otherEntry) {
        int result = compareByPriority(otherEntry);
        if (result == 0) {
            return compareByAscendingFifoIndex(otherEntry);
        }
        return result;
    }

    private int compareByPriority(PriorityEntry otherEntry) {
        return this.priority.compareTo(otherEntry.getPriority());
    }

    private int compareByAscendingFifoIndex(PriorityEntry otherEntry) {
        return this.fifoIndex - otherEntry.getFifoIndex();
    }

    private static Priority calculatePriority(InstructionMessage message) {
        return switch (message.getType()) {
            case A -> Priority.HIGH;
            case B -> Priority.MEDIUM;
            default -> Priority.LOW;
        };
    }

    private enum Priority {

        HIGH,
        MEDIUM,
        LOW

    }

}
