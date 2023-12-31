/**
 * @className CallableTest
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-31 12:48
 */
package com.jupiter.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-31 12:48
 */
@Slf4j
public class CallableTest {
    /*
    1. , new FutureTask(new Callable)
    2. new Thread(FutureTask)
    3. FutureTask.get()
     */

    @Test
    void testCallable() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 100;
            }
        };
        FutureTask futureTask = new FutureTask(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        log.info("futureTask result: ,{}",futureTask.get());
    }
}
