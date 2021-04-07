package uk.co.mruoc.exercises.batch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErrAndOut;

class SimpleSubscriberTest {

    private final SimpleSubscriber<Object> subscriber = new SimpleSubscriber<>();

    @Test
    void shouldLogExceptionOnError() throws Exception {
        Throwable error = new Exception("boom");

        String logs = tapSystemErrAndOut(() -> subscriber.onError(error));

        assertThat(logs).isEqualTo("[Test worker] ERROR uk.co.mruoc.exercises.batch.SimpleSubscriber - boom\n");
    }

}
