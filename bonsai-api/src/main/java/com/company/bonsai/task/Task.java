package com.company.bonsai.task;

import com.company.bonsai.script.Script;
import java.util.List;

public interface Task extends Runnable {

    String getName();

    String getTitle();
    void setTitle(String title);

    Task getParent();
    void setParent(Task parent);

    List<Task> getChildren();

    TaskConfiguration getConfiguration();

    Script getScript();
    void setScript(Script script);

    TaskExecutionInfo getExecutionInfo();

}
