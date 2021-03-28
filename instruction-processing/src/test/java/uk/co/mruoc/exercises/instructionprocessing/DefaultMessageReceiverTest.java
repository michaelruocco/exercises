package uk.co.mruoc.exercises.instructionprocessing;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DefaultMessageReceiverTest {

    private final InstructionQueue queue = mock(InstructionQueue.class);
    private final MessageConverter converter = mock(MessageConverter.class);

    private final DefaultMessageReceiver receiver = new DefaultMessageReceiver(queue, converter);

    @Test
    void shouldConvertInputToInstructionMessageAndEnqueue() {
        String input = MessageMother.defaultMessage();
        InstructionMessage message = givenConvertsTo(input);

        receiver.receive(input);

        ArgumentCaptor<InstructionMessage> captor = ArgumentCaptor.forClass(InstructionMessage.class);
        verify(queue).enqueue(captor.capture());
        assertThat(captor.getValue()).isEqualTo(message);
    }

    private InstructionMessage givenConvertsTo(String input) {
        InstructionMessage message = InstructionMessageMother.defaultMessage();
        given(converter.toInstructionMessage(input)).willReturn(message);
        return message;
    }

}
