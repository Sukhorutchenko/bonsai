package com.company.bonsai.task;

import com.company.bonsai.plugin.EvaluateResource;
import com.company.bonsai.plugin.PluginContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return configuration;
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
        injectConfiguration(engine, configuration);
    }

    private void injectPlugins(ScriptEngine engine, PluginContainer pluginContainer) {
        for (Map.Entry<String, Class> pluginEntry : pluginContainer.getPlugins().entrySet()) {
            evaluateResources(engine, pluginEntry.getValue());
            try {
                engine.put(pluginEntry.getKey(), pluginEntry.getValue().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void evaluateResources(ScriptEngine engine, Class pluginClass) {
        Annotation annotation = pluginClass.getAnnotation(EvaluateResource.class);
        if (annotation != null) {
            ClassLoader classLoader = pluginClass.getClassLoader();
            for (String resourcePath : ((EvaluateResource) annotation).value()) {
                Reader resource = new InputStreamReader(classLoader.getResourceAsStream(resourcePath));
                try {
                    engine.eval(resource);
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void injectConfiguration(ScriptEngine engine, TaskConfiguration configuration) {
        engine.put("args", parseArgsToArray(configuration.getArgsLine()));
        engine.put("argument", parseArgsToFields(configuration.getArgsLine()));
    }

    private String[] parseArgsToArray(String argsLine) {
        return argsLine.split("\\s+");
    }

    private Map<String, String> parseArgsToFields(String argsLine) {
        Map<String, String> args = new HashMap<>();
        List<String> words = Arrays.asList(argsLine.split("\\s+"));
        for (String word : words) {
            if (word.contains(ARGUMENT_EQUAL)) {
                String name = word.substring(0, word.indexOf(ARGUMENT_EQUAL));
                String value = word.substring(word.indexOf(ARGUMENT_EQUAL) + 1);
                args.put(name, value);
            } else {
                args.put(word, word);
            }
        }
        return args;
    }

}
