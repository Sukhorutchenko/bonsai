package com.company.bonsai.task;

import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutionQueue;
import com.company.bonsai.interfaces.task.TaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTaskExecutor implements TaskExecutor {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private TaskExecutionQueue taskExecutionQueue;
    private Thread executorThread;

    public SimpleTaskExecutor(TaskExecutionQueue taskExecutionQueue) {
        this.taskExecutionQueue = taskExecutionQueue;
        taskExecutionQueue.addListener(this);
    }

    public void startExecutor() {
        executorThread = new Thread(this);
        executorThread.start();
    }

    @Override
    public void run() {
        while(true) {
            long interval = 0;
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Task task = taskExecutionQueue.getNextTask();
            if (task != null) {
                executorService.submit(task);
            }
        }
    }

}
