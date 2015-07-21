package com.company.bonsai.plugin;

import java.util.Map;

public interface PluginContainer {

    Class getPluginByName(String name);

    Map<String, Class> getPlugins();

}
