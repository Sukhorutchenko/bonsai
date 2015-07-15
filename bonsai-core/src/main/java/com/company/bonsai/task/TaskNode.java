package com.company.bonsai.task;

import java.util.ArrayList;
import java.util.List;

public class TaskNode extends ScriptEngineTask {

    private final List<TaskNode> childs = new ArrayList<>();
    private TaskNode parent;

    public TaskNode(String name, String title) {
        super(name, title);
    }

    public void add(TaskNode element) {
        childs.add(element);
    }

    public TaskNode get(int index) {
        return childs.get(index);
    }

    public int indexOf(Object obj) {
        return childs.indexOf(obj);
    }

    public void remove(TaskNode element) {
        childs.remove(element);
    }

    public int size() {
        return childs.size();
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public TaskNode getParent() {
        return parent;
    }

    public void setParent(TaskNode parent) {
        this.parent = parent;
    }

}
