package com.company.bonsai.plugin;

public interface Plugin {

    String getTitle();

    String getName();

    PluginConfiguration getConfiguration();

    void setConfiguration(PluginConfiguration configuration);

}
