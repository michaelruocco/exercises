package uk.co.mruoc.exercises.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class ReactiveBatchProcessor implements Processor {

    private final MessageSource messageSource;
    private final MessageHandler messageHandler;
    private final int threads;
    private final int threadPoolQueueSize;

    @Override
    public void start() {
        Scheduler scheduler = threadPoolScheduler(threads, threadPoolQueueSize);
        messageSource.getMessageBatches()
                .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
                .doOnNext(batch -> log.info(batch.toString()))
                .flatMap(batch -> Flux.fromIterable(batch.getMessages()))
                .flatMap(message -> Mono.defer(() -> Mono.just(messageHandler.handleMessage(message)))
                        .subscribeOn(scheduler))
                .subscribeWith(new SimpleSubscriber<>(threads, 1));
    }

    private Scheduler threadPoolScheduler(int poolSize, int queueSize) {
        return Schedulers.fromExecutor(new ThreadPoolExecutor(
                poolSize,
                poolSize,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(queueSize),
                new WaitForCapacityPolicy()
        ));
    }


}
