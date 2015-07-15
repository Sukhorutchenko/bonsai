package com.company.bonsai.plugin;

import java.util.Collection;
import java.util.Iterator;

public interface PluginContainer {

    Plugin getPluginByName(String name);

    Collection<Plugin> getPlugins();

}
