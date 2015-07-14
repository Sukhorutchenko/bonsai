package com.company.bonsai.task;

import java.util.ArrayList;
import java.util.List;

public class TaskNode extends ScriptEngineTask {

    private final List<TaskNode> childs = new ArrayList<>();

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

}
