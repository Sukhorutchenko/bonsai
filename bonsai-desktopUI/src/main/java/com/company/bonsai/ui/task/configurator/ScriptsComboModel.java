package com.company.bonsai.ui.task.configurator;

import com.company.bonsai.script.Script;
import com.company.bonsai.script.ScriptContainer;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class ScriptsComboModel implements ComboBoxModel<Script> {

    private final ScriptContainer scriptContainer;
    private Script selected;

    public ScriptsComboModel(ScriptContainer scriptContainer) {
        this.scriptContainer = scriptContainer;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (Script) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return scriptContainer.getScripts().size();
    }

    @Override
    public Script getElementAt(int index) {
        return scriptContainer.getScripts().get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        //TODO ?
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        //TODO ?
    }

}
