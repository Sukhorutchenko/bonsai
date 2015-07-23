package com.company.bonsai.ui.task.manager;

import com.company.bonsai.plugin.PluginContainer;
import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.Task;
import com.company.bonsai.task.TaskConfiguration;
import com.company.bonsai.task.TaskConfigurationTree;
import com.company.bonsai.task.TaskExecutor;
import com.company.bonsai.task.TaskFactory;
import com.company.bonsai.ui.DialogResult;
import com.company.bonsai.ui.task.configurator.TaskConfigurator;

public class TaskManager {

    private final TaskManagerFrame frame;
    private final TaskConfigurationTree taskConfigurationTree;
    private final TaskFactory taskFactory;
    private final TaskExecutor taskExecutor;
    private final ScriptContainer scriptContainer;
    private final PluginContainer pluginContainer;

    public TaskManager(TaskConfigurationTree taskConfigurationTree, TaskFactory taskFactory, TaskExecutor taskExecutor, ScriptContainer scriptContainer, PluginContainer pluginContainer) {
        this.taskConfigurationTree = taskConfigurationTree;
        this.taskFactory = taskFactory;
        this.taskExecutor = taskExecutor;
        this.scriptContainer = scriptContainer;
        this.pluginContainer = pluginContainer;
        this.frame = createFrame();
    }

    public void addTask(TaskConfiguration parent) {
        TaskConfiguration taskConfiguration = taskFactory.createTaskConfiguration(parent);
        TaskConfigurator taskConfigurator = new TaskConfigurator(taskConfiguration, scriptContainer, pluginContainer);
        if (taskConfigurator.performDialog() == DialogResult.OK) {
            parent.getChildren().add(taskConfiguration);
        }
        taskConfigurationTree.saveConfigurationTree();
    }

    public void removeTask(TaskConfiguration taskConfiguration) {
        TaskConfiguration parent = taskConfiguration.getParent();
        if (parent != null) {
            parent.getChildren().remove(taskConfiguration);
        }
        taskConfigurationTree.saveConfigurationTree();
    }

    public void configureTask(TaskConfiguration taskConfiguration) {
        new TaskConfigurator(taskConfiguration, scriptContainer, pluginContainer).performDialog();
        taskConfigurationTree.saveConfigurationTree();
    }

    public void runTask(TaskConfiguration taskConfiguration) {
        Task task = taskFactory.createTask(taskConfiguration);
        taskExecutor.execute(task);
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public TaskConfigurationTree getTaskConfigurationTree() {
        return taskConfigurationTree;
    }

    public TaskFactory getTaskFactory() {
        return taskFactory;
    }

    private TaskManagerFrame createFrame() {
        return new TaskManagerFrame(this);
    }

}
