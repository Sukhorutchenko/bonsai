package com.company.bonsai.ui.task.manager;

import com.company.bonsai.task.TaskConfiguration;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Vector;

public class TaskTreeModel implements TreeModel {

    private Vector<TreeModelListener> treeModelListeners = new Vector<>();
    private TaskConfiguration rootTaskConfiguration;

    public TaskTreeModel(TaskConfiguration rootTaskConfiguration) {
        this.rootTaskConfiguration = rootTaskConfiguration;
    }

    @Override
    public Object getRoot() {
        return rootTaskConfiguration;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TaskConfiguration) parent).getChildren().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TaskConfiguration) parent).getChildren().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TaskConfiguration) node).getChildren().size() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TaskConfiguration) parent).getChildren().indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.addElement(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.removeElement(l);
    }

}
