package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.plugin.Configuration;
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
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TaskConfiguratorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTabbedPane tabPanel;
    private JTextField taskNameField;
    private JSpinner taskDelaySpinner;
    private JComboBox taskScriptCombo;
    private JTextField argsLineField;

    private final static String TASK_CONFIGURATOR_FRAME_TITLE_SUFFIX = " configuration";
    private TaskConfigurator taskConfigurator;
    private Map<Class /*ConfigClass*/, PluginConfigurationPanel> pluginsConfigPanels = new HashMap<>();

    public TaskConfiguratorDialog(TaskConfigurator taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        setTitle(taskConfigurator.getTaskConfiguration().getName() + TASK_CONFIGURATOR_FRAME_TITLE_SUFFIX);
        setContentPane(contentPane);
        initPluginTabs();
        initWidgets();
        pack();
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
    }

    private void initPluginTabs() {
        Map<String, Class> plugins = taskConfigurator.getPluginContainer().getPlugins();
        for (Map.Entry<String, Class> plugin : plugins.entrySet()) {
            Class pluginClass = plugin.getValue();
            for (Field field : pluginClass.getDeclaredFields()) {
                if (field.getAnnotation(Configuration.class) != null) {
                    PluginConfigurationPanel configPanel = new PluginConfigurationPanel(field.getType());
                    tabPanel.addTab(plugin.getKey(), configPanel.getContentPane());
                    pluginsConfigPanels.put(field.getType(), configPanel);
                }
            }
        }
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

        //add plugins configs
        for (Map.Entry<Class, PluginConfigurationPanel> entry : pluginsConfigPanels.entrySet()) {
            taskConfiguration.getPluginConfigurations().put(entry.getKey(), entry.getValue().getConfiguration());
        }
    }

}
