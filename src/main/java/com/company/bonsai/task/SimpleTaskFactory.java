package com.company.bonsai.task;

import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskFactory;

public class SimpleTaskFactory implements TaskFactory {

    private PluginContainer pluginContainer;
    private ScriptContainer scriptContainer;
    private Task taskTree;

    public SimpleTaskFactory(PluginContainer pluginContainer, ScriptContainer scriptContainer, Task taskTree) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskTree = taskTree;
    }

    @Override
    public Task createTask() {
        return null;
    }

}
