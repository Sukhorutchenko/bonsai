package com.company.bonsai.gui;

import com.company.bonsai.interfaces.plugin.PluginContainer;
import com.company.bonsai.interfaces.script.ScriptContainer;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class SwingDesktopUI implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SwingDesktopUI.class);

    private PluginContainer pluginContainer;
    private ScriptContainer scriptContainer;
    private Task taskTree;
    private TaskFactory taskFactory;
    private TaskExecutor taskExecutor;

    public SwingDesktopUI(PluginContainer pluginContainer,
                          ScriptContainer scriptContainer,
                          Task taskTree,
                          TaskFactory taskFactory,
                          TaskExecutor taskExecutor) {
        this.pluginContainer = pluginContainer;
        this.scriptContainer = scriptContainer;
        this.taskTree = taskTree;
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

        JFrame frame = new JFrame("Bonsai");

//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TaskManagerForm(taskFactory, taskExecutor).getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

}
