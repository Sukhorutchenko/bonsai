package com.company.bonsai.plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPluginContainer implements PluginContainer {

    private final Map<String /*pluginName*/, Plugin> plugins = new HashMap<>();

    public MapPluginContainer(FilePluginLoader pluginLoader) {
        initPlugins(pluginLoader.loadAllPlugins());
    }

    @Override
    public Plugin getPluginByName(String name) {
        return plugins.get(name);
    }

    public Collection<Plugin> getPlugins() {
        return plugins.values();
    }

    private void initPlugins(List<Plugin> pluginList) {
        for (Plugin plugin : pluginList) {
            plugins.put(plugin.getName(), plugin);
        }
    }

}
