package com.company.bonsai.ui.task.manager;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class TaskManagerFrame extends JFrame {

    private final static String TASK_MANAGER_FRAME_TITLE = "Task Manager";
    private TaskManager taskManager;

    public TaskManagerFrame(TaskManager taskManager) throws HeadlessException {
        super(TASK_MANAGER_FRAME_TITLE);

        this.taskManager = taskManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new TaskManagerForm(taskManager).getContentPane());
        pack();
        setLocationRelativeTo(null);
    }

}
