package com.company.bonsai.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginContainerImpl {

    private Map<String, Plugin> plugins = new HashMap<>();
    private List<String> pluginsFiles = new ArrayList<>();

    public PluginContainerImpl() {
        pluginsFiles.add("com.company.bonsai.CaptchaPlugin");
        initPlugins();
    }

    private void initPlugins() {
        for (String pluginClassName : pluginsFiles) {

            try {
                Class clazz = Class.forName(pluginClassName);
                Plugin plugin = (Plugin) clazz.newInstance();
                plugins.put(plugin.getName(), plugin);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public Map<String, Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(Map<String, Plugin> plugins) {
        this.plugins = plugins;
    }

}
