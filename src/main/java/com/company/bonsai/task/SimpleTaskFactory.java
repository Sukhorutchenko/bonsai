package com.company.bonsai.task;

import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTaskFactory implements TaskFactory {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleTaskFactory.class);
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
        ScriptEngineTask task = new ScriptEngineTask();
        task.setScript(scriptContainer.getScriptByName("StubScript"));
        task.setPluginContainer(pluginContainer);
        return task;
    }

}
