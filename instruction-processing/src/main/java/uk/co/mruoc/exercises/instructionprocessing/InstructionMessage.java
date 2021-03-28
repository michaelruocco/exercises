package uk.co.mruoc.exercises.instructionprocessing;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class InstructionMessage {

    private final InstructionType type;
    private final String productCode;
    private final int quantity;
    private final int uom;
    private final Instant timestamp;

}
