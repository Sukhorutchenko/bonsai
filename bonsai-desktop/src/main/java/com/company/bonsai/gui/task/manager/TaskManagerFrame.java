package com.company.bonsai.gui.task.manager;

import com.company.bonsai.task.TaskExecutor;
import com.company.bonsai.task.TaskFactory;
import com.company.bonsai.task.TaskNode;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class TaskManagerFrame extends JFrame {

    private final static String TASK_MANAGER_FRAME_TITLE = "Task Manager";

    public TaskManagerFrame(TaskNode taskTreeRoot, TaskFactory taskFactory, TaskExecutor taskExecutor) throws HeadlessException {
        super(TASK_MANAGER_FRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new TaskManagerForm(taskTreeRoot, taskFactory, taskExecutor).getContentPane());
        pack();
        setLocationRelativeTo(null);
    }

}
