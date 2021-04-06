package uk.co.mruoc.exercises.batch;

import reactor.core.publisher.Flux;

public interface MessageSource {

    Flux<MessageBatch> getMessageBatches();

}
