package uk.co.mruoc.exercises.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
public class TestMessageSource implements MessageSource {

    private final int batches;
    private final int batchSize;

    @Override
    public Flux<MessageBatch> getMessageBatches() {
        return Flux.range(1, batches).map(this::messageBatch);
    }

    private MessageBatch messageBatch(int batchNumber) {
        return new MessageBatch(IntStream.range(0, batchSize)
                .mapToObj(i -> new Message(String.format("%d-%d", batchNumber, i)))
                .collect(Collectors.toList())
        );
    }

}
