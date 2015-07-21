package com.company.bonsai.task;

import com.company.bonsai.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ScriptEngineTaskConfiguration implements TaskConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTaskConfiguration.class);
    private TaskConfiguration parent;
    private final List<TaskConfiguration> children = new ArrayList<>();
    private String name;
    private Script script;
    private String argsLine;
    private long delay;

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
    public Script getScript() {
        if (script != null) {
            return script;
        } else if (parent != null) {
            return parent.getScript();
        }
        return null;
    }

    @Override
    public void setScript(Script script) {
        this.script = script;
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
    public long getDelay() {
        return delay;
    }

    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return name;
    }

}
