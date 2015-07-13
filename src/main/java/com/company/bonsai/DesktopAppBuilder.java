package com.company.bonsai;

import com.company.bonsai.gui.SwingDesktopUI;
import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.TaskExecutionQueue;
import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;
import com.company.bonsai.interfaces.task.TaskTree;
import com.company.bonsai.plugin.MapPluginContainer;
import com.company.bonsai.script.MapScriptContainer;
import com.company.bonsai.task.PriorityExecutionQueue;
import com.company.bonsai.task.SimpleTaskExecutor;
import com.company.bonsai.task.SimpleTaskFactory;
import com.company.bonsai.task.SimpleTaskTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesktopAppBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(DesktopAppBuilder.class);

    public void build() {
        PluginContainer pluginContainer = createPluginContainer();
        LOG.debug("Plugin Container created");

        ScriptContainer scriptContainer = createScriptContainer();
        LOG.debug("Script Container created");

        TaskTree taskTree = createTaskTree();
        LOG.debug("Task Tree created");

        TaskFactory taskFactory = createTaskFactory(pluginContainer, scriptContainer, taskTree);
        LOG.debug("Task Factory created");

        TaskExecutionQueue taskExecutionQueue = createTaskExecutionQueue();
        LOG.debug("Execution Queue created");

        TaskExecutor taskExecutor = createTaskExecutor(taskExecutionQueue);
        LOG.debug("Task Executor created");

        SwingDesktopUI swingDesktopUI = createUI(pluginContainer, scriptContainer, taskTree,
                taskFactory, taskExecutionQueue, taskExecutor);
        LOG.debug("GUI created");

    }

    private PluginContainer createPluginContainer() {
        return new MapPluginContainer();
    }

    private ScriptContainer createScriptContainer() {
        return new MapScriptContainer();
    }

    private TaskTree createTaskTree() {
        return new SimpleTaskTree();
    }

    private TaskFactory createTaskFactory(PluginContainer pluginContainer,
                                          ScriptContainer scriptContainer,
                                          TaskTree taskTree) {
        return new SimpleTaskFactory(pluginContainer, scriptContainer, taskTree);
    }

    private TaskExecutionQueue createTaskExecutionQueue() {
        return new PriorityExecutionQueue();
    }

    private TaskExecutor createTaskExecutor(TaskExecutionQueue taskExecutionQueue) {
        return new SimpleTaskExecutor(taskExecutionQueue);
    }

    private SwingDesktopUI createUI(PluginContainer pluginContainer,
                                    ScriptContainer scriptContainer,
                                    TaskTree taskTree,
                                    TaskFactory taskFactory,
                                    TaskExecutionQueue taskExecutionQueue,
                                    TaskExecutor taskExecutor) {
        return new SwingDesktopUI(pluginContainer, scriptContainer, taskTree,
                taskFactory, taskExecutionQueue, taskExecutor);
    }

}
