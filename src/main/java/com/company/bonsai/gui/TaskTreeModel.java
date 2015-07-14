package com.company.bonsai.gui;

import com.company.bonsai.task.ScriptEngineTask;
import com.company.bonsai.task.TaskNode;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TaskTreeModel implements TreeModel {

    private TaskNode rootTaskNode;

    public TaskTreeModel(TaskNode rootTaskNode) {
        this.rootTaskNode = rootTaskNode;
    }

    @Override
    public Object getRoot() {
        return rootTaskNode;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TaskNode) parent).get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TaskNode) parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ScriptEngineTask.class.equals(node.getClass());
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TaskNode) parent).indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

}
