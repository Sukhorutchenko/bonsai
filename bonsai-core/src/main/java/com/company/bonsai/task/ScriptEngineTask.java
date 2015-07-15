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
import java.util.Collection;
import java.util.List;

public class ScriptEngineTask implements Task {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTask.class);
    private static final String NASHORN_ENGINE_NAME = "nashorn";
    private String name;
    private String title;
    private Task parent;
    private final List<Task> children = new ArrayList<>();
    private Script script;
    private PluginContainer pluginContainer;

    public ScriptEngineTask(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public void run() {
        ScriptEngine engine = createEngine();
        prepareResources(engine);
        try {
            engine.eval(script.getScriptBody());
        } catch (ScriptException e) {
            LOG.error("Script failed", e);
        }
    }

    @Override
    public TaskExecutionInfo getExecutionInfo() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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
        return script;
    }

    @Override
    public void setScript(Script script) {
        this.script = script;
    }

    @Override
    public TaskConfiguration getConfiguration() {
        return null;
    }

//    public void setConfiguration(TaskConfiguration configuration) {
//
//    }

    public void setName(String name) {
        this.name = name;
    }

    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    public void setPluginContainer(PluginContainer pluginContainer) {
        this.pluginContainer = pluginContainer;
    }

    private ScriptEngine createEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        return manager.getEngineByName(NASHORN_ENGINE_NAME);
    }

    private void prepareResources(ScriptEngine engine) {
        injectEnvVars(engine, LOG);
        injectPlugins(engine, pluginContainer);
        injectLibs(engine, "libs");
        injectConfigurations(engine, "config");
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
