package com.company.bonsai.gui;

import com.company.bonsai.interfaces.task.TaskExecutor;
import com.company.bonsai.interfaces.task.TaskFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerForm {
    private JPanel contentPane;
    private JTree tree1;
    private JTable table1;
    private JButton runTaskButton;



    public TaskManagerForm(TaskFactory taskFactory, TaskExecutor taskExecutor) {
        runTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action");
                taskExecutor.execute(taskFactory.createTask());
            }
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
