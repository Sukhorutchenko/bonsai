package com.company.bonsai.task;

public class SimpleTaskFactory implements TaskFactory {

    @Override
    public Task createTask(Task parent) {
        return new ScriptEngineTask(parent, NEW_TASK_NAME);
    }

}
