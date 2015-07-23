package com.company.bonsai.task;

import com.company.bonsai.plugin.Configuration;
import com.company.bonsai.plugin.Inject;
import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptEngineTask implements Task {

    private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineTask.class);
    private static final String NASHORN_ENGINE_NAME = "nashorn";
    private PluginContainer pluginContainer;
    private TaskConfiguration taskConfiguration;

    public ScriptEngineTask(TaskConfiguration taskConfiguration, PluginContainer pluginContainer) {
        this.taskConfiguration = taskConfiguration;
        this.pluginContainer = pluginContainer;
    }

    @Override
    public TaskConfiguration getTaskConfiguration() {
        return taskConfiguration;
    }

    @Override
    public void run() {
        ScriptEngine engine = createEngine();
        prepareResources(engine);
        try {
            engine.eval(taskConfiguration.getScript().getScriptBody());
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
        injectConfiguration(engine, taskConfiguration);
    }

    private void injectPlugins(ScriptEngine engine, PluginContainer pluginContainer) {
        for (Map.Entry<String, Class> pluginEntry : pluginContainer.getPlugins().entrySet()) {
            evaluateResources(engine, pluginEntry.getValue());
            try {
                String pluginName = pluginEntry.getKey();
                Object pluginInstance = createPluginInstance(pluginEntry.getValue());
                engine.put(pluginName, pluginInstance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Object createPluginInstance(Class pluginClass) throws IllegalAccessException, InstantiationException {
        Object pluginInstance = pluginClass.newInstance();
        for (Field field : pluginClass.getDeclaredFields()) {
            if (field.getAnnotation(Configuration.class) != null) {
                Class configurationClass = field.getType();
                Object pluginConfiguration = getPluginConfiguration(configurationClass);
                setFieldValue(pluginInstance, field, pluginConfiguration);
            }
            if (field.getAnnotation(Inject.class) != null) {
                Class requiredClass = field.getType();
                Object environmentComponent = getEnvironmentComponent(requiredClass);
                setFieldValue(pluginInstance, field, environmentComponent);
            }
        }
        return pluginInstance;
    }

    private Object getPluginConfiguration(Class pluginConfigurationClass) {
        return taskConfiguration.getPluginConfiguration(pluginConfigurationClass);
    }

    private Object getEnvironmentComponent(Class environmentComponentClass) {
        if (TaskConfiguration.class.equals(environmentComponentClass)) {
            return taskConfiguration;
        }
        return null;
    }

    private void setFieldValue(Object instance, Field field, Object value) {
        boolean wasAccessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(wasAccessible);
    }

    private void evaluateResources(ScriptEngine engine, Class pluginClass) {
        Annotation annotation = pluginClass.getAnnotation(Plugin.class);
        Plugin pluginAnnotation = (Plugin) annotation;
        if (pluginAnnotation != null && pluginAnnotation.libs().length > 0) {
            ClassLoader classLoader = pluginClass.getClassLoader();
            for (String resourcePath : pluginAnnotation.libs()) {
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
