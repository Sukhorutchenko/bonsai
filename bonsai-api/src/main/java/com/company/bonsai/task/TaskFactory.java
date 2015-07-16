package com.company.bonsai.task;

public interface TaskFactory {

    String NEW_TASK_NAME = "New task";

    Task createTask(Task parent);

}
