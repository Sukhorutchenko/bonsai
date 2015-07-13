package com.company.bonsai.interfaces.plugin;

public interface Plugin {

    String getTitle();

    String getName();

    PluginConfiguration getConfiguration();

    void setConfiguration(PluginConfiguration configuration);

}
