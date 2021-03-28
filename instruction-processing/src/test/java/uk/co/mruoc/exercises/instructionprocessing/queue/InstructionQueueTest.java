package uk.co.mruoc.exercises.instructionprocessing.queue;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.instructionprocessing.InstructionMessage;
import uk.co.mruoc.exercises.instructionprocessing.InstructionMessageMother;
import uk.co.mruoc.exercises.instructionprocessing.InstructionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.exercises.instructionprocessing.InstructionType.*;

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
        InstructionMessage message = mockMessage();

        queue.enqueue(message);

        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    void shouldReturnButNotRemoveNextMessageFromPeek() {
        InstructionMessage message = mockMessage();
        queue.enqueue(message);

        assertThat(queue.peek()).isEqualTo(message);
        assertThat(queue.peek()).isEqualTo(message);
    }

    @Test
    void shouldReturnNumberOfMessagesEnqueued() {
        queue.enqueue(mockMessage());
        queue.enqueue(mockMessage());

        assertThat(queue.count()).isEqualTo(2);
    }

    @Test
    void shouldReturnAndRemoveNextMessageFromDequeue() {
        InstructionMessage expectedMessage = mockMessage();
        queue.enqueue(expectedMessage);

        InstructionMessage message = queue.dequeue();

        assertThat(message).isEqualTo(expectedMessage);
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void shouldThrowExceptionIfAttemptToPeekFromEmptyQueue() {
        Throwable error = catchThrowable(queue::peek);

        assertThat(error).isInstanceOf(InstructionQueueEmptyException.class);
    }

    @Test
    void shouldThrowExceptionIfAttemptToDequeueFromEmptyQueue() {
        Throwable error = catchThrowable(queue::dequeue);

        assertThat(error).isInstanceOf(InstructionQueueEmptyException.class);
    }

    @Test
    void shouldReturnMessagesInPriorityOrderBasedOnInstructionType() {
        InstructionMessage typeBMessage = InstructionMessageMother.withType(B);
        InstructionMessage typeCMessage = InstructionMessageMother.withType(C);
        InstructionMessage typeAMessage = InstructionMessageMother.withType(A);
        InstructionMessage typeDMessage = InstructionMessageMother.withType(D);

        queue.enqueue(typeBMessage);
        queue.enqueue(typeCMessage);
        queue.enqueue(typeAMessage);
        queue.enqueue(typeDMessage);

        assertThat(queue.dequeue()).isEqualTo(typeAMessage);
        assertThat(queue.dequeue()).isEqualTo(typeBMessage);
        assertThat(queue.dequeue()).isEqualTo(typeCMessage);
        assertThat(queue.dequeue()).isEqualTo(typeDMessage);
    }

    @Test
    void shouldReturnMessagesInInsertionIfPrioritiesAreEqual() {
        InstructionMessage typeDMessage1 = InstructionMessageMother.builder().type(D).quantity(1).build();
        InstructionMessage typeCMessage = InstructionMessageMother.withType(C);
        InstructionMessage typeDMessage2 = InstructionMessageMother.builder().type(D).quantity(2).build();
        InstructionMessage typeAMessage1 = InstructionMessageMother.builder().type(A).quantity(1).build();
        InstructionMessage typeAMessage2 = InstructionMessageMother.builder().type(A).quantity(2).build();

        queue.enqueue(typeDMessage1);
        queue.enqueue(typeCMessage);
        queue.enqueue(typeDMessage2);
        queue.enqueue(typeAMessage1);
        queue.enqueue(typeAMessage2);

        assertThat(queue.dequeue()).isEqualTo(typeAMessage1);
        assertThat(queue.dequeue()).isEqualTo(typeAMessage2);
        assertThat(queue.dequeue()).isEqualTo(typeDMessage1);
        assertThat(queue.dequeue()).isEqualTo(typeCMessage);
        assertThat(queue.dequeue()).isEqualTo(typeDMessage2);
    }

    private static InstructionMessage mockMessage() {
        InstructionMessage message = mock(InstructionMessage.class);
        given(message.getType()).willReturn(InstructionType.A);
        return message;
    }

}
