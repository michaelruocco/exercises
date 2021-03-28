package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MessageConverterTest {

    private final MessageConverter converter = new MessageConverter();

    @Test
    void shouldConvertValidMessage() {
        String input = MessageMother.validMessage();

        InstructionMessage message = converter.toInstructionMessage(input);

        assertThat(message).isEqualTo(InstructionMessageMother.validMessage());
    }

    @Test
    void shouldThrowExceptionIfInstructionTypeIsNotValid() {
        String invalidInstructionType = "Z";
        String input = MessageMother.withInstructionType(invalidInstructionType);

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid instruction type %s", invalidInstructionType))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionIfProductCodeIsNotValid() {
        String invalidProductCode = "1234";
        String input = MessageMother.withProductCode(invalidProductCode);

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid product code %s", invalidProductCode));
    }

    @Test
    void shouldThrowExceptionIfQuantityIsNotValid() {
        String invalidQuantity = "A";
        String input = MessageMother.withQuantity(invalidQuantity);

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid quantity %s", invalidQuantity))
                .hasCauseInstanceOf(NumberFormatException.class);
    }

    @Test
    void shouldThrowExceptionIfUomIsNotValid() {
        String invalidUom = "B";
        String input = MessageMother.withUom(invalidUom);

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid uom %s", invalidUom))
                .hasCauseInstanceOf(NumberFormatException.class);
    }

    @Test
    void shouldThrowExceptionIfTimestampIsNotValid() {
        String invalidTimestamp = "Blah";
        String input = MessageMother.withTimestamp(invalidTimestamp);

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid timestamp %s", invalidTimestamp))
                .hasCauseInstanceOf(DateTimeParseException.class);
    }

}
