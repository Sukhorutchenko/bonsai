package com.company.bonsai.task;

import com.company.bonsai.interfaces.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ScriptEngineTaskNode extends ScriptEngineTask {

    private final List<Task> childs = new ArrayList<>();

    public void add(Task element) {
        childs.add(element);
    }

    public void remove(Task element) {
        childs.remove(element);
    }

    public int size() {
        return childs.size();
    }

}
