package uk.co.mruoc.exercises.batch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class WaitForCapacityPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        try {
            log.debug("adding rejected runnable to queue {} for executor {}", runnable, executor);
            executor.getQueue().put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.debug(e.getMessage(), e);
            throw new RejectedExecutionException(e);
        }
    }

}
