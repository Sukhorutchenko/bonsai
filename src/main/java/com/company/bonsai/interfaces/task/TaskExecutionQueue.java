package com.company.bonsai.interfaces.task;

import java.time.LocalTime;

public interface TaskExecutionQueue {

    LocalTime getNextTaskTime();

    Task getNextTask();

    void add(Task task);

    void addListener(ExecutionQueueListener listener);

}
