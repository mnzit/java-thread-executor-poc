package com.f1soft.concurrency.poc;

import com.f1soft.concurrency.poc.task.FakeTask;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<FakeTask> tasks = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            tasks.add(new FakeTask("(Task)-[" + i + "]", 1));
        }
        try {
            Date date = new Date();
            List<Future<Object>> taskResponse = executorService.invokeAll(tasks);

            for (Future<Object> taskData : taskResponse) {
                System.out.println(taskData.get());
            }

            System.out.println("took time: " + Double.valueOf(Long.toString(timeDiff(date))) / 1000 + "s");

        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("Exception :" + ex.getMessage());
        }

        executorService.shutdown();
    }

    public static long timeDiff(Date startDateTime) {
        Date endDate = new Date();
        long diff = (endDate.getTime() - startDateTime.getTime());
        return diff;
    }

}
