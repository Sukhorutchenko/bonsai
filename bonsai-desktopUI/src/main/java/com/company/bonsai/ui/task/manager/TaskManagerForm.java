package com.company.bonsai.ui.task.manager;

import com.company.bonsai.task.TaskConfiguration;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;

public class TaskManagerForm {

    private TaskManager taskManager;

    private JPanel contentPane;
    private JTree taskTreeWidget;
    private JTable table1;
    private JButton runTaskButton;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    private JButton configTaskButton;

    public TaskManagerForm(TaskManager taskManager) {
        this.taskManager = taskManager;
        initWidgets();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void initWidgets() {
        initTaskTree();
        initAddTaskButton();
        initRemoveTaskButton();
        initConfigTaskButton();

        runTaskButton.addActionListener(e -> taskManager.runTask(getSelectedOrRoot()));
    }

    private void initTaskTree() {
        taskTreeWidget.setModel(new TaskTreeModel(taskManager.getTaskConfigurationTree().getRootTaskConfiguration()));
        taskTreeWidget.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    private void initAddTaskButton() {
        addTaskButton.addActionListener(e -> {
            taskManager.addTask(getSelectedOrRoot());
            taskTreeWidget.updateUI();
        });
    }

    private void initRemoveTaskButton() {
        removeTaskButton.addActionListener(e -> {
            taskManager.removeTask(getSelectedOrRoot());
            taskTreeWidget.updateUI();
        });
    }

    private void initConfigTaskButton() {
        configTaskButton.addActionListener(e -> {
            taskManager.configureTask(getSelectedOrRoot());
            taskTreeWidget.updateUI();
        });
    }

    private TaskConfiguration getSelectedOrRoot() {
        TaskConfiguration taskConfiguration = (TaskConfiguration) taskTreeWidget.getLastSelectedPathComponent();
        if (taskConfiguration == null) {
            taskConfiguration = taskManager.getTaskConfigurationTree().getRootTaskConfiguration();
        }
        return taskConfiguration;
    }

}
