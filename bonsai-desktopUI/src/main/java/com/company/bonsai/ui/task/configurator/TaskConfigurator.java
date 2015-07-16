package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.Task;
import com.company.bonsai.ui.DialogResult;

public class TaskConfigurator {

    private final Task task;
    private final ScriptContainer scriptContainer;
    private TaskConfiguratorDialog dialog;
    private DialogResult dialogResult;

    public TaskConfigurator(Task task, ScriptContainer scriptContainer) {
        this.task = task;
        this.scriptContainer = scriptContainer;
        this.dialog = createDialog();
    }

    public Task getTask() {
        return task;
    }

    public ScriptContainer getScriptContainer() {
        return scriptContainer;
    }

    public DialogResult performDialog() {
        dialog.setVisible(true);
        return dialogResult;
    }

    public DialogResult getDialogResult() {
        return dialogResult;
    }

    public void setDialogResult(DialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }

    private TaskConfiguratorDialog createDialog() {
        return new TaskConfiguratorDialog(this);
    }

}
