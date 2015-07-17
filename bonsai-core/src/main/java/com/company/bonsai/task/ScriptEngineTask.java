package com.company.bonsai.task;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginContainer;

import com.company.bonsai.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class ScriptEngineTask implements Task {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTask.class);
    private static final String NASHORN_ENGINE_NAME = "nashorn";

    private Task parent;
    private final List<Task> children = new ArrayList<>();
    private String name;
    private Script script;
    private long delay;

    public ScriptEngineTask(String name) {
        this.name = name;
    }

    public ScriptEngineTask(Task parent, String name) throws IllegalArgumentException {
        if (parent != null) {
            this.parent = parent;
        } else {
            throw new IllegalArgumentException("Failed to create task without parent task");
        }
        this.name = name;
    }

    @Override
    public void run() {
        ScriptEngine engine = createEngine();
        prepareResources(engine);
        try {
            engine.eval(getScript().getScriptBody());
        } catch (ScriptException e) {
            LOG.error("Script failed", e);
        }
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
    public Task getParent() {
        return parent;
    }

    @Override
    public void setParent(Task parent) {
        this.parent = parent;
    }

    @Override
    public List<Task> getChildren() {
        return children;
    }

    @Override
    public Script getScript() {
        if (script != null) {
            return script;
        } else if (parent != null){
            return parent.getScript();
        }
        return null;
    }

    @Override
    public void setScript(Script script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }

    private ScriptEngine createEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        return manager.getEngineByName(NASHORN_ENGINE_NAME);
    }

    private void prepareResources(ScriptEngine engine) {
//        injectEnvVars(engine, LOG);
//        injectPlugins(engine, pluginContainer);
//        injectLibs(engine, "libs");
//        injectConfigurations(engine, "config");
    }

    private void injectEnvVars(ScriptEngine engine, Logger log) {
        engine.put("log", log);
    }

    private void injectPlugins(ScriptEngine engine, PluginContainer pluginContainer) {
        for (Plugin plugin : pluginContainer.getPlugins()) {
            engine.put(plugin.getName(), plugin);
        }
    }

    private void injectLibs(ScriptEngine engine, String libs) {

    }

    private void injectConfigurations(ScriptEngine engine, String config) {

    }

}
