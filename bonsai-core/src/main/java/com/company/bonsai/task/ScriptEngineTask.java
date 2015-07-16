package com.company.bonsai.task;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

public class ScriptEngineTask implements Task {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTask.class);
    private static final String NASHORN_ENGINE_NAME = "nashorn";

    private Task parent;
    private final List<Task> children = new ArrayList<>();
    private String name;

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
//        ScriptEngine engine = createEngine();
//        prepareResources(engine);
//        try {
//            engine.eval(script.getScriptBody());
//        } catch (ScriptException e) {
//            LOG.error("Script failed", e);
//        }
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
    public String toString() {
        return name;
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
