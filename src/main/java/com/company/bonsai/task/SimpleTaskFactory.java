package com.company.bonsai.task;

import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskFactory;
import com.company.bonsai.interfaces.task.TaskTree;

public class SimpleTaskFactory implements TaskFactory {

    private PluginContainer pluginContainer;
    private ScriptContainer scriptContainer;
    private TaskTree taskTree;

    public SimpleTaskFactory(PluginContainer pluginContainer, ScriptContainer scriptContainer, TaskTree taskTree) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskTree = taskTree;
    }

    @Override
    public Task createTask() {
        return null;
    }

}
