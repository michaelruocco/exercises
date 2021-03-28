package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageReceiverIntegrationTest {

    private final InstructionQueue queue = new InstructionQueue();
    private final MessageReceiver receiver = new DefaultMessageReceiver(queue);

    @Test
    void shouldReceiveMessage() {
        String inputMessage = MessageMother.defaultMessage();

        receiver.receive(inputMessage);

        assertThat(queue.isEmpty()).isFalse();
    }

}
