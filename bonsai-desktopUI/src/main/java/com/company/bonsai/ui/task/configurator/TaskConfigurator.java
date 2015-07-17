package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.TaskConfiguration;
import com.company.bonsai.ui.DialogResult;

public class TaskConfigurator {

    private final TaskConfiguration taskConfiguration;
    private final ScriptContainer scriptContainer;
    private TaskConfiguratorDialog dialog;
    private DialogResult dialogResult;

    public TaskConfigurator(TaskConfiguration taskConfiguration, ScriptContainer scriptContainer) {
        this.taskConfiguration = taskConfiguration;
        this.scriptContainer = scriptContainer;
        this.dialog = createDialog();
    }

    public TaskConfiguration getTaskConfiguration() {
        return taskConfiguration;
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
