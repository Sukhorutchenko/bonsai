package com.company.bonsai.task;

import com.company.bonsai.plugin.PluginContainer;
import com.company.bonsai.script.ScriptContainer;

public class SimpleTaskFactory implements TaskFactory {

    private PluginContainer pluginContainer;
    private ScriptContainer scriptContainer;

    public SimpleTaskFactory(ScriptContainer scriptContainer, PluginContainer pluginContainer) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
    }

    @Override
    public TaskConfiguration createTaskConfiguration(TaskConfiguration parent) {
        return new ScriptEngineTaskConfiguration(parent, NEW_TASK_NAME);
    }

    @Override
    public Task createTask(TaskConfiguration configuration) {
        return new ScriptEngineTask(configuration, scriptContainer, pluginContainer);
    }

}
