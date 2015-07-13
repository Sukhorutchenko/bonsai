package com.company.bonsai.task;

import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTaskExecutor implements TaskExecutor {

    private ExecutorService executorService = Executors.newCachedThreadPool();

}
