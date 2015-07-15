package com.company.bonsai.task;

import com.company.bonsai.plugin.PluginContainer;
import com.company.bonsai.script.ScriptContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTaskFactory implements TaskFactory {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleTaskFactory.class);
    private PluginContainer pluginContainer;
    private ScriptContainer scriptContainer;
    private TaskNode taskTreeRoot;

    public SimpleTaskFactory(PluginContainer pluginContainer, ScriptContainer scriptContainer, TaskNode taskTreeRoot) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskTreeRoot = taskTreeRoot;
    }

    @Override
    public Task createTask() {
        ScriptEngineTask task = new ScriptEngineTask("stub-script", "Stub Script");
        task.setScript(scriptContainer.getScriptByName("StubScript"));
        task.setPluginContainer(pluginContainer);
        return task;
    }

}