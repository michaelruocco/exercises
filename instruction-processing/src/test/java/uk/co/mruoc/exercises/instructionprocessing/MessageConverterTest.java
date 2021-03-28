package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import static java.time.Instant.EPOCH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MessageConverterTest {

    private static final Instant NOW = Instant.now();

    private final Clock clock = Clock.fixed(NOW, ZoneId.systemDefault());
    private final MessageConverter converter = new MessageConverter(clock);

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

    @Test
    void shouldThrowExceptionIfTimestampIsInFuture() {
        Instant timestamp = NOW.plusMillis(1);
        String input = MessageMother.withTimestamp(timestamp.toString());

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("timestamp %s cannot be in future current time is %s", timestamp, NOW));
    }

    @Test
    void shouldThrowExceptionIfTimestampIsNotGreaterThanUnixEpoch() {
        Instant timestamp = EPOCH;
        String input = MessageMother.withTimestamp(timestamp.toString());

        Throwable error = catchThrowable(() -> converter.toInstructionMessage(input));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("timestamp %s must be after unix epoch %s", timestamp, EPOCH));
    }

}
