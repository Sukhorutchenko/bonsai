package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.plugin.configuration.TextField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PluginConfigurationPanel {

    private JPanel contentPane;
    private int panelHeight = 50;
    private int comCount = 0;

    Map<String /*fieldName*/, JTextField> textFieldsMap = new HashMap<>();
    private Object pluginConfiguration;

    public PluginConfigurationPanel(Object pluginConfiguration) {
        this.pluginConfiguration = pluginConfiguration;
        contentPane.setLayout(null);
        initWidgets();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void initWidgets() {
        for (Field field : pluginConfiguration.getClass().getDeclaredFields()) {
            if (field.getAnnotation(TextField.class) != null) {

                boolean wasAccessible = field.isAccessible();
                field.setAccessible(true);
                String textFieldValue = null;
                try {
                     textFieldValue = (String) field.get(pluginConfiguration);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(wasAccessible);

                JPanel panel = getPanel();
                JLabel label = new JLabel(field.getName());
                label.setBounds(0, 10, 400, 10);
                panel.add(label);
                JTextField textField = new JTextField();
                textField.setText(textFieldValue);
                textField.setBounds(0, 25, 350, 20);
                panel.add(textField);
                textFieldsMap.put(field.getName(), textField);
            }
        }
        contentPane.updateUI();
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, comCount * panelHeight, 500, panelHeight);
        comCount++;
        contentPane.add(panel);
        return panel;
    }

    public void submitPluginConfiguration() {
        for (Map.Entry<String, JTextField> entry : textFieldsMap.entrySet()) {
            try {
                Field field = pluginConfiguration.getClass().getDeclaredField(entry.getKey());
                boolean wasAccessible = field.isAccessible();
                field.setAccessible(true);
                field.set(pluginConfiguration, entry.getValue().getText());
                field.setAccessible(wasAccessible);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
