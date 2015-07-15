package com.company.bonsai.gui;

import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;
import com.company.bonsai.task.TaskNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class TaskManagerForm {
    private JPanel contentPane;
    private JTree taskTreeWidget;
    private JTable table1;
    private JButton runTaskButton;
    private JButton addTaskButton;
    private JButton removeTaskButton;

    private TaskNode taskTreeRoot;
    private TaskFactory taskFactory;
    private TaskExecutor taskExecutor;

    public TaskManagerForm(TaskNode taskTreeRoot, TaskFactory taskFactory, TaskExecutor taskExecutor) {
        this.taskTreeRoot = taskTreeRoot;
        this.taskFactory = taskFactory;
        this.taskExecutor = taskExecutor;
        initWidgets();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void initWidgets() {
        taskTreeWidget.setModel(new TaskTreeModel(taskTreeRoot));
        taskTreeWidget.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);
        taskTreeWidget.setCellRenderer(renderer);

        addTaskButton.addActionListener(e -> {
            TaskNode node = (TaskNode) taskTreeWidget.getLastSelectedPathComponent();
            node.add(new TaskNode("nodeName", "nodeTitle"));
            taskTreeWidget.updateUI();
        });

        removeTaskButton.addActionListener(e -> {
            TaskNode node = (TaskNode) taskTreeWidget.getLastSelectedPathComponent();
            TaskNode parent = (TaskNode)taskTreeWidget.getSelectionModel().getSelectionPath()
                    .getParentPath().getLastPathComponent();
            parent.remove(node);
            taskTreeWidget.updateUI();
        });

        runTaskButton.addActionListener(e -> taskExecutor.execute(taskFactory.createTask()));
    }

}
