package com.company.bonsai.task;

import com.company.bonsai.interfaces.task.TaskElement;
import com.company.bonsai.interfaces.task.TaskTree;

public class SimpleTaskTree implements TaskTree {

    private TaskElement rootElement;

    public SimpleTaskTree() {
    }

    public SimpleTaskTree(TaskElement rootElement) {
        this.rootElement = rootElement;
    }

    @Override
    public TaskElement getRoot() {
        return rootElement;
    }

}
