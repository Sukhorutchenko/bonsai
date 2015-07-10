package com.company.bonsai;

import com.company.bonsai.plugin.PluginContainer;
import com.company.bonsai.script.ScriptContainer;
import my.task.TaskExecutor;
import my.task.TaskFactory;

public interface ApplicationContext {

    TaskExecutor getTaskExecutor();

    TaskFactory getTaskFactory();

    PluginContainer getPluginContainer();

    ScriptContainer getScriptContainer();

    ComponentLocator getComponentLocator();

}
