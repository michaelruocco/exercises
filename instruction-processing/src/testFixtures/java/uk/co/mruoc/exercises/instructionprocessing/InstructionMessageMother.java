package uk.co.mruoc.exercises.instructionprocessing;

import java.time.Instant;

public interface InstructionMessageMother {

    static InstructionMessage defaultMessage() {
        return builder().build();
    }

    static InstructionMessage.InstructionMessageBuilder builder() {
        return InstructionMessage.builder()
            .type(InstructionType.A)
                .productCode("MZ89")
                .quantity(5678)
                .uom(50)
                .timestamp(Instant.parse("2015-03-05T10:04:56.012Z"));
    }

}
