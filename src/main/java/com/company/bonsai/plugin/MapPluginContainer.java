package com.company.bonsai.plugin;

import com.company.bonsai.interfaces.plugin.Plugin;
import com.company.bonsai.interfaces.plugin.PluginContainer;

import java.util.HashMap;
import java.util.Map;

public class MapPluginContainer implements PluginContainer {

    private final Map<String /*pluginName*/, Plugin> plugins = new HashMap<>();

    public MapPluginContainer() {
        initPlugins(plugins);
    }

    @Override
    public Plugin getPluginByName(String name) {
        return plugins.get(name);
    }

    public Map<String, Plugin> getPlugins() {
        return plugins;
    }

    private void initPlugins(Map<String, Plugin> plugins) {
        Plugin stubPlugin = new StubPlugin();
        plugins.put(stubPlugin.getName(), stubPlugin);
    }

}
