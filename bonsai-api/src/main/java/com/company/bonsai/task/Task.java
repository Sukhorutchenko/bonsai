package com.company.bonsai.task;

import com.company.bonsai.script.Script;

public interface Task extends Runnable {

    String getName();

    String getTitle();

    Script getScript();

    TaskExecutionInfo getExecutionInfo();

    TaskConfiguration getConfiguration();

    void setConfiguration(TaskConfiguration configuration);

}
