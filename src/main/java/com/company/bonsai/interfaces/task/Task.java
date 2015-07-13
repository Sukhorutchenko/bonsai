package com.company.bonsai.interfaces.task;

import com.company.bonsai.interfaces.script.Script;

public interface Task extends Runnable {

    String getName();

    String getTitle();

    Script getScript();

    TaskExecutionInfo getExecutionInfo();

    TaskConfiguration getConfiguration();

    void setConfiguration(TaskConfiguration configuration);

}
