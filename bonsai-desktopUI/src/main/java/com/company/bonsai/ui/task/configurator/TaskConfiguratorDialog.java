package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.ui.DialogResult;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskConfiguratorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private final static String TASK_CONFIGURATOR_FRAME_TITLE = "Task Configurator";
    private TaskConfigurator taskConfigurator;

    public TaskConfiguratorDialog(TaskConfigurator taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        setTitle(TASK_CONFIGURATOR_FRAME_TITLE);
        setContentPane(contentPane);
        initWidgets();
        pack();
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
    }

    private void initWidgets() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        taskConfigurator.setDialogResult(DialogResult.OK);
        dispose();
    }

    private void onCancel() {
        taskConfigurator.setDialogResult(DialogResult.CANCEL);
        dispose();
    }

}
