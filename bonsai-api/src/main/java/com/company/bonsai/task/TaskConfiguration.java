package com.company.bonsai.task;

import com.company.bonsai.script.Script;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface TaskConfiguration extends Serializable {

    String getName();
    void setName(String name);

    TaskConfiguration getParent();
    void setParent(TaskConfiguration parent);

    List<TaskConfiguration> getChildren();

    Script getScript();
    void setScript(Script script);

    String getArgsLine();
    void setArgsLine(String argsLine);

    Map<Class /*pluginConfigurationClass*/, Object> getPluginConfigurations();

    long getDelay();
    void setDelay(long delay);

}
