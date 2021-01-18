package com.f1soft.concurrency.poc.task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTaskListener;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class FakeTask implements Callable<Object>, ManagedTaskListener {

    private final String taskName;
    private final int duration;

    public FakeTask(String taskName, int duration) {
        this.taskName = taskName;
        this.duration = duration;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Begining " + this.taskName + " of thread : " + Thread.currentThread().getName());
        Thread.sleep(duration * 1000);
        System.out.println("Finished " + this.taskName + " of thread : " + Thread.currentThread().getName());
        return "Response for " + taskName + " " + duration;
    }

    @Override
    public void taskSubmitted(Future<?> f, ManagedExecutorService es,
            Object obj) {
        System.out.println("Task Submitted! " + f);
    }

    @Override
    public void taskDone(Future<?> f, ManagedExecutorService es, Object obj,
            Throwable exc) {
        System.out.println("Task DONE! " + f);
    }

    @Override
    public void taskStarting(Future<?> f, ManagedExecutorService es,
            Object obj) {
        System.out.println("Task Starting! " + f);
    }

    @Override
    public void taskAborted(Future<?> f, ManagedExecutorService es,
            Object obj, Throwable exc) {
        System.out.println("Task Aborted! " + f);
    }
}
