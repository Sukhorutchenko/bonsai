package com.company.bonsai.task;

public interface TaskFactory {

    String NEW_TASK_NAME = "New task";

    TaskConfiguration createTaskConfiguration(TaskConfiguration parent);

    Task createTask(TaskConfiguration configuration);

}
