package com.company.bonsai.interfaces.task;

public interface Task extends Runnable {

    TaskExecutionInfo getExecutionInfo();

    String getTitle();

    String getName();

    TaskConfiguration getConfiguration();

    void setConfiguration(TaskConfiguration configuration);

}
