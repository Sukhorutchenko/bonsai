package com.company.bonsai.gui;

import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;
import com.company.bonsai.task.TaskNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerForm {
    private JPanel contentPane;
    private JTree taskTreeWidget;
    private JTable table1;
    private JButton runTaskButton;

    private TaskTreeModel taskTreeModel;

    public TaskManagerForm(TaskNode taskTreeRoot, TaskFactory taskFactory, TaskExecutor taskExecutor) {
        this.taskTreeModel = new TaskTreeModel(taskTreeRoot);
        runTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskExecutor.execute(taskFactory.createTask());
            }
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        taskTreeWidget = new JTree(taskTreeModel);
    }
}
