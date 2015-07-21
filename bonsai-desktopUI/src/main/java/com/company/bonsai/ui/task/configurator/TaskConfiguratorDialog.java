package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.script.Script;
import com.company.bonsai.script.ScriptContainer;
import com.company.bonsai.task.TaskConfiguration;
import com.company.bonsai.task.TaskFactory;
import com.company.bonsai.ui.DialogResult;
import org.springframework.util.StringUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskConfiguratorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTabbedPane tabbedPane1;
    private JTextField taskNameField;
    private JSpinner taskDelaySpinner;
    private JComboBox taskScriptCombo;
    private JTextField argsLineField;

    private final static String TASK_CONFIGURATOR_FRAME_TITLE_SUFFIX = " configuration";
    private TaskConfigurator taskConfigurator;

    public TaskConfiguratorDialog(TaskConfigurator taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        setTitle(taskConfigurator.getTaskConfiguration().getName() + TASK_CONFIGURATOR_FRAME_TITLE_SUFFIX);
        setContentPane(contentPane);
        initWidgets();
        pack();
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
    }

    private void initWidgets() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        updateTaskConfigurationUI(taskConfigurator.getTaskConfiguration(), taskConfigurator.getScriptContainer());

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        submitTaskConfiguration(taskConfigurator.getTaskConfiguration());
        taskConfigurator.setDialogResult(DialogResult.OK);
        dispose();
    }

    private void onCancel() {
        taskConfigurator.setDialogResult(DialogResult.CANCEL);
        dispose();
    }

    private void updateTaskConfigurationUI(TaskConfiguration taskConfiguration, ScriptContainer scriptContainer) {
        taskNameField.setText(taskConfiguration.getName());
        taskDelaySpinner.setValue((int) taskConfiguration.getDelay());
        taskScriptCombo.setModel(new ScriptsComboModel(scriptContainer));
        taskScriptCombo.setSelectedItem(taskConfiguration.getScript());
        argsLineField.setText(taskConfiguration.getArgsLine());
    }

    private void submitTaskConfiguration(TaskConfiguration taskConfiguration) {
        String name = taskNameField.getText();
        if (StringUtils.isEmpty(name)) {
            name = TaskFactory.NEW_TASK_NAME;
        }
        taskConfiguration.setName(name);
        taskConfiguration.setScript((Script) taskScriptCombo.getSelectedItem());
        taskConfiguration.setArgsLine(argsLineField.getText());
        long delay = (int) taskDelaySpinner.getValue();
        taskConfiguration.setDelay(delay);
    }

}
