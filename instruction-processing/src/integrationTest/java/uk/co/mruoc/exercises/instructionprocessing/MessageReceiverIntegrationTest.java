package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.instructionprocessing.queue.InstructionQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MessageReceiverIntegrationTest {

    private final InstructionQueue queue = new InstructionQueue();
    private final MessageReceiver receiver = new DefaultMessageReceiver(queue);

    @Test
    void shouldReceiveMessage() {
        String inputMessage = MessageMother.validMessage();

        receiver.receive(inputMessage);

        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    void shouldIncrementCountWhenMessageReceived() {
        String inputMessage = MessageMother.validMessage();

        receiver.receive(inputMessage);

        assertThat(queue.count()).isEqualTo(1);
    }

    @Test
    void shouldReceiveValidMessageConvertedCorrectly() {
        String inputMessage = MessageMother.validMessage();

        receiver.receive(inputMessage);

        assertThat(queue.peek()).isEqualTo(InstructionMessageMother.validMessage());
    }

    @Test
    void shouldReceiveValidMessageConvertedCorrectlyAndRemoveIfDequeued() {
        String inputMessage = MessageMother.validMessage();

        receiver.receive(inputMessage);

        assertThat(queue.dequeue()).isEqualTo(InstructionMessageMother.validMessage());
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void shouldThrowExceptionIfInvalidMessageIsReceived() {
        String invalidInstructionType = "Z";
        String inputMessage = MessageMother.builder().instructionType("Z").asString();

        Throwable error = catchThrowable(() -> receiver.receive(inputMessage));

        assertThat(error)
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage(String.format("invalid instruction type %s", invalidInstructionType));
    }

}
