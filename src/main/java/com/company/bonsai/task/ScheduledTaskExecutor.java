package com.company.bonsai.task;

import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskExecutor implements TaskExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTaskExecutor.class);
    private static final int THREADS_NUMBER = 10;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(THREADS_NUMBER);

    @Override
    public void execute(Task task) {
        LOG.debug("execute task - " + task);
        executorService.schedule(task, 0L, TimeUnit.MILLISECONDS);
    }

}
