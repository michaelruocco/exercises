package uk.co.mruoc.exercises.instructionprocessing;

import java.time.Instant;

public class MessageConverter {

    public InstructionMessage toInstructionMessage(String message) {
        String[] parts = message.split(" ");
        return InstructionMessage.builder()
                .type(InstructionType.valueOf(parts[1]))
                .productCode(parts[2])
                .quantity(Integer.parseInt(parts[3]))
                .uom(Integer.parseInt(parts[4]))
                .timestamp(Instant.parse(parts[5]))
                .build();
    }

}
