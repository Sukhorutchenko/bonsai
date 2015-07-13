package com.company.bonsai.interfaces.task;

public interface Task extends Runnable, TaskElement {

    TaskExecutionInfo getExecutionInfo();

}
