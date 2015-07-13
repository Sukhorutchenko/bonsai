package com.company.bonsai.task;

import com.company.bonsai.interfaces.plugin.Plugin;
import com.company.bonsai.interfaces.task.Task;
import com.company.bonsai.interfaces.task.TaskConfiguration;
import com.company.bonsai.interfaces.task.TaskExecutionInfo;
import com.company.bonsai.plugin.MapPluginContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

public class ScriptEngineTask implements Task, Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTask.class);
    public static final String NASHORN_ENGINE_NAME = "nashorn";

    private MapPluginContainer mapPluginContainer;

    @Override
    public void run() {
        ScriptEngine engine = createEngine();
        prepareResources(engine);


        //engine.eval(new FileReader("script.js"));
    }

    @Override
    public TaskExecutionInfo getExecutionInfo() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public TaskConfiguration getConfiguration() {
        return null;
    }

    @Override
    public void setConfiguration(TaskConfiguration configuration) {

    }

    public MapPluginContainer getMapPluginContainer() {
        return mapPluginContainer;
    }

    public void setMapPluginContainer(MapPluginContainer mapPluginContainer) {
        this.mapPluginContainer = mapPluginContainer;
    }

    private ScriptEngine createEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        return manager.getEngineByName(NASHORN_ENGINE_NAME);
    }

    private void prepareResources(ScriptEngine engine) {
        injectEnvVars(engine, LOG);
        injectPlugins(engine, mapPluginContainer);
        injectLibs(engine, "libs");
        injectConfigurations(engine, "config");
    }

    private void injectEnvVars(ScriptEngine engine, Logger log) {
        engine.put("log", log);
    }

    private void injectPlugins(ScriptEngine engine, MapPluginContainer mapPluginContainer) {

        for (Map.Entry<String, Plugin> plugin : mapPluginContainer.getPlugins().entrySet()) {
            engine.put(plugin.getKey(), plugin.getValue());
        }

    }

    private void injectLibs(ScriptEngine engine, String libs) {

    }

    private void injectConfigurations(ScriptEngine engine, String config) {

    }


}
