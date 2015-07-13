package com.company.bonsai.interfaces.task;

public interface TaskExecutionInfo {

    TaskExecutionState getState();

    void setState(TaskExecutionState state);

}
