package uk.co.mruoc.exercises.batch;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Slf4j
class ReactiveBatchProcessorTest {

    private static final int BATCHES = 10;
    private static final int BATCH_SIZE = 3;
    private static final int THREADS = 2;
    private static final int THREAD_POOL_QUEUE_SIZE = 10;
    private static final int EXPECTED_MESSAGES = BATCHES * BATCH_SIZE;

    private final MessageSource messageSource = new TestMessageSource(BATCHES, BATCH_SIZE);
    private final TestMessageHandler handler = new TestMessageHandler();

    @Test
    void shouldProcessAllMessagesOnThreadsFromSameThreadPool() {
        Processor processor = new ReactiveBatchProcessor(
                messageSource,
                handler,
                THREADS,
                THREAD_POOL_QUEUE_SIZE);

        processor.start();

        waitForAllMessagesToBeProcessed();

        assertThat(handler.threadNames().size()).isEqualTo(THREADS);
    }

    private void waitForAllMessagesToBeProcessed() {
        await().atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> assertThat(handler.getProcessedMessages()).isEqualTo(EXPECTED_MESSAGES));
    }

}
