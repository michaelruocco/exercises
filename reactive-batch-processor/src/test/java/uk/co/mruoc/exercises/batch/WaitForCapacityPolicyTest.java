package uk.co.mruoc.exercises.batch;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class WaitForCapacityPolicyTest {

    private final WaitForCapacityPolicy policy = new WaitForCapacityPolicy();

    @Test
    void shouldThrowRejectedExecutionExceptionIfInterrupted() throws InterruptedException {
        Runnable runnable = mock(Runnable.class);
        BlockingQueue<Runnable> queue = mock(BlockingQueue.class);
        ThreadPoolExecutor executor = givenExecutorWithQueue(queue);
        doThrow(InterruptedException.class).when(queue).put(runnable);

        Throwable error = catchThrowable(() -> policy.rejectedExecution(runnable, executor));

        assertThat(error)
                .isInstanceOf(RejectedExecutionException.class)
                .hasCauseInstanceOf(InterruptedException.class);
    }

    private ThreadPoolExecutor givenExecutorWithQueue(BlockingQueue<Runnable> queue) {
        ThreadPoolExecutor executor = mock(ThreadPoolExecutor.class);
        given(executor.getQueue()).willReturn(queue);
        return executor;
    }

}
