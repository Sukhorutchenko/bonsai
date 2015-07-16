package com.company.bonsai.ui.task.manager;

import com.company.bonsai.task.Task;
import com.company.bonsai.ui.task.configurator.TaskConfigurator;

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

        //runTaskButton.addActionListener(e -> taskExecutor.execute(taskFactory.createTask()));
    }

    private void initTaskTree() {
        taskTreeWidget.setModel(new TaskTreeModel(taskManager.getRootTask()));
        taskTreeWidget.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    private void initAddTaskButton() {
        addTaskButton.addActionListener(e -> {
            Task task = (Task) taskTreeWidget.getLastSelectedPathComponent();
            if (task == null) {
                task = taskManager.getRootTask();
            }
            taskManager.addTask(task);
            taskTreeWidget.updateUI();
        });
    }

    private void initRemoveTaskButton() {
        removeTaskButton.addActionListener(e -> {
//            TaskNode node = (TaskNode) taskTreeWidget.getLastSelectedPathComponent();
//            TaskNode parent = (TaskNode)taskTreeWidget.getSelectionModel().getSelectionPath()
//                    .getParentPath().getLastPathComponent();
//            parent.remove(node);
            taskTreeWidget.updateUI();
        });
    }

}
