package uk.co.mruoc.exercises.instructionprocessing.queue;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.instructionprocessing.InstructionMessage;

@RequiredArgsConstructor
@Data
public class PriorityEntry {

    private final int insertionIndex;
    private final InstructionMessage message;
    private final Priority priority;

    public PriorityEntry(int insertionIndex, InstructionMessage message) {
        this.insertionIndex = insertionIndex;
        this.message = message;
        this.priority = calculatePriority(message);
    }

    private static Priority calculatePriority(InstructionMessage message) {
        return switch (message.getType()) {
            case A -> Priority.HIGH;
            case B -> Priority.MEDIUM;
            default -> Priority.LOW;
        };
    }

}
