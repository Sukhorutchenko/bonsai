package com.company.bonsai.plugin;

public interface Plugin {

    String getName();

    Object getFacade();

    PluginConfiguration getConfiguration();

    void setConfiguration(PluginConfiguration configuration);

}
