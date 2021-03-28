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
    void shouldNotBeEmptyIfMessageEnqueued() {
        InstructionMessage message = mock(InstructionMessage.class);

        queue.enqueue(message);

        assertThat(queue.isEmpty()).isFalse();
    }

}
