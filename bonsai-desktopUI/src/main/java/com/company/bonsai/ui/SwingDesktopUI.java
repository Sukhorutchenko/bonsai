package com.company.bonsai.ui;

import com.company.bonsai.task.TaskConfigurationTree;
import com.company.bonsai.ui.task.manager.TaskManager;
import com.company.bonsai.plugin.PluginContainer;
import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.TaskExecutor;
import com.company.bonsai.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class SwingDesktopUI implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SwingDesktopUI.class);
    private PluginContainer pluginContainer;
    private ScriptContainer scriptContainer;
    private TaskConfigurationTree taskConfigurationTree;
    private TaskFactory taskFactory;
    private TaskExecutor taskExecutor;

    public SwingDesktopUI(PluginContainer pluginContainer,
                          ScriptContainer scriptContainer,
                          TaskConfigurationTree taskConfigurationTree,
                          TaskFactory taskFactory,
                          TaskExecutor taskExecutor) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskConfigurationTree = taskConfigurationTree;
        this.taskFactory = taskFactory;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOG.error("Failed to set system look and feel to UI");
        }
        new TaskManager(taskConfigurationTree, taskFactory, taskExecutor, scriptContainer, pluginContainer).showFrame();
    }

}
