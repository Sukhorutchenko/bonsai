package com.company.bonsai.task;

import com.company.bonsai.plugin.PluginContainer;

public class SimpleTaskFactory implements TaskFactory {

    private PluginContainer pluginContainer;

    public SimpleTaskFactory(PluginContainer pluginContainer) {
        this.pluginContainer = pluginContainer;
    }

    @Override
    public TaskConfiguration createTaskConfiguration(TaskConfiguration parent) {
        return new ScriptEngineTaskConfiguration(parent, NEW_TASK_NAME);
    }

    @Override
    public Task createTask(TaskConfiguration configuration) {
        return new ScriptEngineTask(configuration, pluginContainer);
    }

}
