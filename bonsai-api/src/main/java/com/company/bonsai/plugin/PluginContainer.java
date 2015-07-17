package com.company.bonsai.plugin;

import java.util.Collection;

public interface PluginContainer {

    Plugin getPluginByName(String name);

    Collection<Plugin> getPlugins();

}
