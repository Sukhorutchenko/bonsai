package com.company.bonsai.task;

public interface TaskExecutionInfo {

    TaskExecutionState getState();

    void setState(TaskExecutionState state);

}
