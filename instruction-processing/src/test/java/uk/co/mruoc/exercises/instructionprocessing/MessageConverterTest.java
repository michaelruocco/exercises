package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MessageConverterTest {

    private final MessageConverter converter = new MessageConverter();

    @Test
    void shouldConvertValidMessage() {
        String input = MessageMother.defaultMessage();

        InstructionMessage message = converter.toInstructionMessage(input);

        assertThat(message).isEqualTo(InstructionMessageMother.defaultMessage());
    }

    @Test
    void shouldThrowExceptionIfInstructionTypeIsNotValid() {
        String invalidInstructionType = "Z";
        String input = MessageMother.withInstructionType(invalidInstructionType);

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid instruction type %s", invalidInstructionType));
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

}
