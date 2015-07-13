package com.company.bonsai.plugin;

import com.company.bonsai.interfaces.plugin.Plugin;
import com.company.bonsai.interfaces.plugin.PluginContainer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

    public Collection<Plugin> getPlugins() {
        return plugins.values();
    }

    private void initPlugins(Map<String, Plugin> plugins) {
        Plugin stubPlugin = new StubPlugin("StubPlugin");
        plugins.put(stubPlugin.getName(), stubPlugin);
    }

}
