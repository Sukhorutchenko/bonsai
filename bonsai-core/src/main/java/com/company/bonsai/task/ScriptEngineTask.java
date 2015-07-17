package com.company.bonsai.task;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineTask implements Task {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTask.class);
    private static final String NASHORN_ENGINE_NAME = "nashorn";
    private PluginContainer pluginContainer;
    private TaskConfiguration configuration;

    public ScriptEngineTask(TaskConfiguration configuration, PluginContainer pluginContainer) {
        this.configuration = configuration;
        this.pluginContainer = pluginContainer;
    }

    @Override
    public TaskConfiguration getConfiguration() {
        return null;
    }

    @Override
    public void run() {
        ScriptEngine engine = createEngine();
        prepareResources(engine);
        try {
            engine.eval(configuration.getScript().getScriptBody());
        } catch (ScriptException e) {
            LOG.error("Script failed", e);
        }
    }

    private ScriptEngine createEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        return manager.getEngineByName(NASHORN_ENGINE_NAME);
    }

    private void prepareResources(ScriptEngine engine) {
        injectPlugins(engine, pluginContainer);
    }

    private void injectPlugins(ScriptEngine engine, PluginContainer pluginContainer) {
        for (Plugin plugin : pluginContainer.getPlugins()) {
            engine.put(plugin.getName(), plugin.getFacade());
        }
    }

}
