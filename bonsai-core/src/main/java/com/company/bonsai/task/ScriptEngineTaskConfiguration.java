package com.company.bonsai.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptEngineTaskConfiguration implements TaskConfiguration, Serializable {

    private transient static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTaskConfiguration.class);
    private TaskConfiguration parent;
    private final List<TaskConfiguration> children = new ArrayList<>();
    private String name;
    private String scriptName;
    private String argsLine;
    private long delay;
    private final Map<Class, Object> pluginConfigurations = new HashMap<>();

    /**
     * Constructor for root element
     * @param name
     */
    public ScriptEngineTaskConfiguration(String name) {
        this.name = name;
    }

    public ScriptEngineTaskConfiguration(TaskConfiguration parent, String name) throws IllegalArgumentException {
        if (parent == null) {
            throw new IllegalArgumentException("Failed to create task without parent task");
        }
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public TaskConfiguration getParent() {
        return parent;
    }

    @Override
    public void setParent(TaskConfiguration parent) {
        this.parent = parent;
    }

    @Override
    public List<TaskConfiguration> getChildren() {
        return children;
    }

    @Override
    public String getScriptName() {
        if (scriptName != null) {
            return scriptName;
        } else if (parent != null) {
            return parent.getScriptName();
        }
        return null;
    }

    @Override
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public String getArgsLine() {
        if (argsLine != null) {
            return argsLine;
        } else if (parent != null) {
            return parent.getArgsLine();
        }
        return null;
    }

    @Override
    public void setArgsLine(String argsLine) {
        this.argsLine = argsLine;
    }

    @Override
    public Storage getStorage() {
        return null;
    }

    @Override
    public void setStorage(Storage storage) {

    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public Object getPluginConfiguration(Class pluginConfigurationClass) {
        return pluginConfigurations.get(pluginConfigurationClass);
    }

    @Override
    public void setPluginConfiguration(Class pluginConfigurationClass, Object pluginConfiguration) {
        pluginConfigurations.put(pluginConfigurationClass, pluginConfiguration);
    }

    @Override
    public String toString() {
        return name;
    }

}
