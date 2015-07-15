package com.company.bonsai;

import com.company.bonsai.gui.SwingDesktopUI;
import com.company.bonsai.plugin.PluginContainer;
import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.TaskExecutor;
import com.company.bonsai.task.TaskFactory;
import com.company.bonsai.plugin.MapPluginContainer;
import com.company.bonsai.script.MapScriptContainer;
import com.company.bonsai.task.TaskNode;
import com.company.bonsai.task.ScheduledTaskExecutor;
import com.company.bonsai.task.SimpleTaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DesktopAppBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(DesktopAppBuilder.class);

    public static void main(String[] args) {
        new DesktopAppBuilder().build();
    }

    public void build() {
        PluginContainer pluginContainer = createPluginContainer();
        LOG.debug("Plugin Container created");

        ScriptContainer scriptContainer = createScriptContainer();
        LOG.debug("Script Container created");

        TaskNode taskTreeRoot = createTaskTree();
        LOG.debug("Task Tree created");

        TaskFactory taskFactory = createTaskFactory(pluginContainer, scriptContainer, taskTreeRoot);
        LOG.debug("Task Factory created");

        TaskExecutor taskExecutor = createTaskExecutor();
        LOG.debug("Task Executor created");

        SwingDesktopUI swingDesktopUI = createUI(pluginContainer, scriptContainer, taskTreeRoot,
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

    private TaskNode createTaskTree() {
        return new TaskNode("/", "/");
    }

    private TaskFactory createTaskFactory(PluginContainer pluginContainer,
                                          ScriptContainer scriptContainer,
                                          TaskNode taskTreeRoot) {
        return new SimpleTaskFactory(pluginContainer, scriptContainer, taskTreeRoot);
    }

    private TaskExecutor createTaskExecutor() {
        return new ScheduledTaskExecutor();
    }

    private SwingDesktopUI createUI(PluginContainer pluginContainer,
                                    ScriptContainer scriptContainer,
                                    TaskNode taskTreeRoot,
                                    TaskFactory taskFactory,
                                    TaskExecutor taskExecutor) {
        return new SwingDesktopUI(pluginContainer, scriptContainer, taskTreeRoot,
                taskFactory, taskExecutor);
    }

}
