package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.task.Task;
import com.company.bonsai.ui.DialogResult;

public class TaskConfigurator {

    private TaskConfiguratorDialog dialog;
    private DialogResult dialogResult;
    private Task task;

    public TaskConfigurator(Task task) {
        this.task = task;
        this.dialog = createDialog();
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
