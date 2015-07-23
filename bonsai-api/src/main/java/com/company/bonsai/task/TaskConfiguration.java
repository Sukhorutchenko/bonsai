package com.company.bonsai.task;

import com.company.bonsai.script.Script;

import java.io.Serializable;
import java.util.List;

public interface TaskConfiguration extends Serializable {

    String ROOT_CONFIGURATION_NAME = "root";
    String getName();
    void setName(String name);

    TaskConfiguration getParent();
    void setParent(TaskConfiguration parent);

    List<TaskConfiguration> getChildren();

    String getScriptName();
    void setScriptName(String scriptName);

    String getArgsLine();
    void setArgsLine(String argsLine);

    Storage getStorage();
    void setStorage(Storage storage);

    Object getPluginConfiguration(Class pluginConfigurationClass);
    void setPluginConfiguration(Class pluginConfigurationClass, Object pluginConfiguration);

    long getDelay();
    void setDelay(long delay);

}
