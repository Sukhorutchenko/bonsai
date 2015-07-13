package com.company.bonsai.task;

import com.company.bonsai.interfaces.task.ExecutionQueueListener;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutionQueue;

import java.time.LocalTime;
import java.util.PriorityQueue;

public class PriorityExecutionQueue implements TaskExecutionQueue {

    private PriorityQueue<Task> executionQueue = new PriorityQueue<>();

    @Override
    public LocalTime getNextTaskTime() {
        return null;
    }

    @Override
    public Task getNextTask() {
        return null;
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void addListener(ExecutionQueueListener listener) {

    }
}
