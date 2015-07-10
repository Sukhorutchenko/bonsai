package com.company.bonsai.script;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginContainerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

public class TaskImpl implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(TaskImpl.class);
    public static final String NASHORN_ENGINE_NAME = "nashorn";

    private PluginContainerImpl pluginContainer;


    @Override
    public void run() {
        ScriptEngine engine = createEngine();
        prepareResources(engine);


        //engine.eval(new FileReader("script.js"));
    }

    public PluginContainerImpl getPluginContainer() {
        return pluginContainer;
    }

    public void setPluginContainer(PluginContainerImpl pluginContainerImpl) {
        this.pluginContainer = pluginContainerImpl;
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

    private void injectPlugins(ScriptEngine engine, PluginContainerImpl pluginContainerImpl) {

        for (Map.Entry<String, Plugin> plugin : pluginContainerImpl.getPlugins().entrySet()) {
            engine.put(plugin.getKey(), plugin.getValue());
        }

    }

    private void injectLibs(ScriptEngine engine, String libs) {

    }

    private void injectConfigurations(ScriptEngine engine, String config) {

    }

}
