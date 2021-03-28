package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InstructionQueueTest {

    private final InstructionQueue queue = new InstructionQueue();

    @Test
    void shouldBeEmptyByDefault() {
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void shouldHaveZeroCountByDefault() {
        assertThat(queue.count()).isZero();
    }

    @Test
    void shouldNotBeEmptyIfMessageEnqueued() {
        InstructionMessage message = mock(InstructionMessage.class);

        queue.enqueue(message);

        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    void shouldReturnButNotRemoveNextMessageFromPeek() {
        InstructionMessage message = mock(InstructionMessage.class);
        queue.enqueue(message);

        assertThat(queue.peek()).isEqualTo(message);
        assertThat(queue.peek()).isEqualTo(message);
    }

    @Test
    void shouldReturnNumberOfMessagesEnqueued() {
        queue.enqueue(mock(InstructionMessage.class));
        queue.enqueue(mock(InstructionMessage.class));

        assertThat(queue.count()).isEqualTo(2);
    }

    @Test
    void shouldReturnAndRemoveNextMessageFromDequeue() {
        InstructionMessage expectedMessage = mock(InstructionMessage.class);
        queue.enqueue(expectedMessage);

        InstructionMessage message = queue.dequeue();

        assertThat(message).isEqualTo(expectedMessage);
        assertThat(queue.isEmpty()).isTrue();
    }

}
