package uk.co.mruoc.exercises.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@RequiredArgsConstructor
@Slf4j
public class SimpleSubscriber<T> implements Subscriber<T> {

    private final int initialFetchCount;
    private final int onNextFetchCount;

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(initialFetchCount);
        log.info("subscribed");
    }

    @Override
    public void onNext(T message) {
        subscription.request(onNextFetchCount);
    }

    @Override
    public void onError(Throwable t) {
        log.error("error");
    }

    @Override
    public void onComplete() {
        log.info("completed");
    }

}
