package com.company.bonsai.ui.task.manager;

import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.Task;
import com.company.bonsai.task.TaskExecutor;
import com.company.bonsai.task.TaskFactory;
import com.company.bonsai.ui.DialogResult;
import com.company.bonsai.ui.task.configurator.TaskConfigurator;

public class TaskManager {

    private final TaskManagerFrame frame;
    private final Task rootTask;
    private final TaskFactory taskFactory;
    private final TaskExecutor taskExecutor;
    private final ScriptContainer scriptContainer;

    public TaskManager(Task rootTask, TaskFactory taskFactory, TaskExecutor taskExecutor, ScriptContainer scriptContainer) {
        this.rootTask = rootTask;
        this.taskFactory = taskFactory;
        this.taskExecutor = taskExecutor;
        this.scriptContainer = scriptContainer;
        this.frame = createFrame();
    }

    public void addTask(Task parent) {
        Task task = taskFactory.createTask(parent);
        TaskConfigurator taskConfigurator = new TaskConfigurator(task, scriptContainer);
        if (taskConfigurator.performDialog() == DialogResult.OK) {
            parent.getChildren().add(task);
        }
    }

    public void removeTask(Task task) {
        Task parent = task.getParent();
        if (parent != null) {
            parent.getChildren().remove(task);
        }
    }

    public void configureTask(Task task) {
        new TaskConfigurator(task, scriptContainer).performDialog();
    }

    public void runTask(Task task) {
        taskExecutor.execute(task);
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public Task getRootTask() {
        return rootTask;
    }

    public TaskFactory getTaskFactory() {
        return taskFactory;
    }

    private TaskManagerFrame createFrame() {
        return new TaskManagerFrame(this);
    }

}
