package com.company.bonsai.gui;

import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.TaskExecutionQueue;
import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;
import com.company.bonsai.interfaces.task.TaskTree;

public class SwingDesktopUI {

    PluginContainer pluginContainer;
    ScriptContainer scriptContainer;
    TaskTree taskTree;
    TaskFactory taskFactory;
    TaskExecutionQueue taskExecutionQueue;
    TaskExecutor taskExecutor;

    public SwingDesktopUI(PluginContainer pluginContainer,
                          ScriptContainer scriptContainer,
                          TaskTree taskTree,
                          TaskFactory taskFactory,
                          TaskExecutionQueue taskExecutionQueue,
                          TaskExecutor taskExecutor) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskTree = taskTree;
        this.taskFactory = taskFactory;
        this.taskExecutionQueue = taskExecutionQueue;
        this.taskExecutor = taskExecutor;
    }

}
