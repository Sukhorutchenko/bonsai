package com.company.bonsai.plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapPluginContainer implements PluginContainer {

    private final Map<String /*pluginName*/, Class> pluginMap;

    public MapPluginContainer(FilePluginLoader pluginLoader) {
        this.pluginMap = loadPluginMap(pluginLoader);
    }

    @Override
    public Class getPluginByName(String name) {
        return pluginMap.get(name);
    }

    @Override
    public Map<String, Class> getPlugins() {
        return Collections.unmodifiableMap(pluginMap);
    }

    private Map<String, Class> loadPluginMap(FilePluginLoader pluginLoader) {
        Map<String, Class> pluginMap = new HashMap<>();
        for (Class pluginClass : pluginLoader.loadPluginClasses()) {
            pluginMap.put(getPluginName(pluginClass), pluginClass);
        }
        return pluginMap;
    }

    private String getPluginName(Class pluginClass) {
        return ((Plugin) pluginClass.getAnnotation(Plugin.class)).name();
    }

}
