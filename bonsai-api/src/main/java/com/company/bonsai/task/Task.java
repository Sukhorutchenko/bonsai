package com.company.bonsai.task;

import com.company.bonsai.script.Script;

import java.io.Serializable;
import java.util.List;

public interface Task extends Runnable, Serializable {

    String getName();
    void setName(String name);

    Task getParent();
    void setParent(Task parent);

    List<Task> getChildren();

    Script getScript();
    void setScript(Script script);

    long getDelay();
    void setDelay(long delay);

}
