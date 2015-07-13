package com.company.bonsai.gui;

import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;

public class SwingDesktopUI {

    PluginContainer pluginContainer;
    ScriptContainer scriptContainer;
    Task taskTree;
    TaskFactory taskFactory;
    TaskExecutor taskExecutor;

    public SwingDesktopUI(PluginContainer pluginContainer,
                          ScriptContainer scriptContainer,
                          Task taskTree,
                          TaskFactory taskFactory,
                          TaskExecutor taskExecutor) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskTree = taskTree;
        this.taskFactory = taskFactory;
        this.taskExecutor = taskExecutor;
    }

}
