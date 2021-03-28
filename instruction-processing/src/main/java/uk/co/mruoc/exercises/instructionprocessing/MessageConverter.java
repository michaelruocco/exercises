package uk.co.mruoc.exercises.instructionprocessing;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class MessageConverter {

    private static final Pattern PRODUCT_CODE_PATTERN = Pattern.compile("^[A-Z]{2}[0-9]{2}$");

    public InstructionMessage toInstructionMessage(String message) {
        String[] parts = message.split(" ");
        return InstructionMessage.builder()
                .type(toInstructionType(parts[1]))
                .productCode(toProductCode(parts[2]))
                .quantity(toQuantity(parts[3]))
                .uom(toUom(parts[4]))
                .timestamp(toTimestamp(parts[5]))
                .build();
    }

    private static InstructionType toInstructionType(String type) {
        try {
            return InstructionType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new InvalidMessageException(String.format("invalid instruction type %s", type), e);
        }
    }

    private static String toProductCode(String code) {
        if (!PRODUCT_CODE_PATTERN.matcher(code).matches()) {
            throw new InvalidMessageException(String.format("invalid product code %s", code));
        }
        return code;
    }

    private static int toQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new InvalidMessageException(String.format("invalid quantity %s", quantity), e);
        }
    }

    private static int toUom(String uom) {
        try {
            return Integer.parseInt(uom);
        } catch (NumberFormatException e) {
            throw new InvalidMessageException(String.format("invalid uom %s", uom), e);
        }
    }
    
    private static Instant toTimestamp(String timestamp) {
        try {
            return Instant.parse(timestamp);
        } catch (DateTimeParseException e) {
            throw new InvalidMessageException(String.format("invalid timestamp %s", timestamp), e);
        }
    }

}
