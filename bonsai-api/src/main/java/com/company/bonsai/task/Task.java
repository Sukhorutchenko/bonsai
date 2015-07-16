package com.company.bonsai.task;

import java.util.List;

public interface Task extends Runnable {

    String getName();
    void setName(String name);

    Task getParent();
    void setParent(Task parent);

    List<Task> getChildren();

}
