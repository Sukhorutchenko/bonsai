package com.company.bonsai;

import com.company.bonsai.gui.SwingDesktopUI;
import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;
import com.company.bonsai.plugin.MapPluginContainer;
import com.company.bonsai.script.MapScriptContainer;
import com.company.bonsai.task.ScriptEngineTaskNode;
import com.company.bonsai.task.ScheduledTaskExecutor;
import com.company.bonsai.task.SimpleTaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DesktopAppBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(DesktopAppBuilder.class);

    public void build() {
        PluginContainer pluginContainer = createPluginContainer();
        LOG.debug("Plugin Container created");

        ScriptContainer scriptContainer = createScriptContainer();
        LOG.debug("Script Container created");

        Task taskTree = createTaskTree();
        LOG.debug("Task Tree created");

        TaskFactory taskFactory = createTaskFactory(pluginContainer, scriptContainer, taskTree);
        LOG.debug("Task Factory created");

        TaskExecutor taskExecutor = createTaskExecutor();
        LOG.debug("Task Executor created");

        SwingDesktopUI swingDesktopUI = createUI(pluginContainer, scriptContainer, taskTree,
                taskFactory, taskExecutor);
        LOG.debug("GUI created");

        SwingUtilities.invokeLater(swingDesktopUI);
    }

    private PluginContainer createPluginContainer() {
        return new MapPluginContainer();
    }

    private ScriptContainer createScriptContainer() {
        return new MapScriptContainer();
    }

    private Task createTaskTree() {
        return new ScriptEngineTaskNode();
    }

    private TaskFactory createTaskFactory(PluginContainer pluginContainer,
                                          ScriptContainer scriptContainer,
                                          Task taskTree) {
        return new SimpleTaskFactory(pluginContainer, scriptContainer, taskTree);
    }

    private TaskExecutor createTaskExecutor() {
        return new ScheduledTaskExecutor();
    }

    private SwingDesktopUI createUI(PluginContainer pluginContainer,
                                    ScriptContainer scriptContainer,
                                    Task taskTree,
                                    TaskFactory taskFactory,
                                    TaskExecutor taskExecutor) {
        return new SwingDesktopUI(pluginContainer, scriptContainer, taskTree,
                taskFactory, taskExecutor);
    }

}
