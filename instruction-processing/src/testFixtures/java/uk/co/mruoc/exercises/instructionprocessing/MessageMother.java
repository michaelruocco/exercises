package uk.co.mruoc.exercises.instructionprocessing;

import lombok.Builder;

import java.util.Arrays;
import java.util.Collection;

public class MessageMother {

    private MessageMother() {
        // utility class
    }

    public static String defaultMessage() {
        return builder().asString();
    }

    public static String withInstructionType(String instructionType) {
        return builder().instructionType(instructionType).asString();
    }

    public static String withProductCode(String productCode) {
        return builder().productCode(productCode).asString();
    }

    private static Message.MessageBuilder builder() {
        return Message.builder();
    }

    @Builder
    private static class Message {
        @Builder.Default
        private final String prefix = "InstructionMessage";
        @Builder.Default
        private final String instructionType = "A";
        @Builder.Default
        private final String productCode = "MZ89";
        @Builder.Default
        private final String quantity = "5678";
        @Builder.Default
        private final String uom = "50";
        @Builder.Default
        private final String timestamp = "2015-03-05T10:04:56.012Z";

        public String asString() {
            return String.join(" ", asValuesCollection());
        }

        private Collection<String> asValuesCollection() {
            return Arrays.asList(prefix, instructionType, productCode, quantity, uom, timestamp);
        }

        public static class MessageBuilder {

            public String asString() {
                return build().asString();
            }

        }
    }

}
