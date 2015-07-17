package com.company.bonsai.plugin;

public interface Plugin {

    String getName();

    Class getFacade();

    PluginConfiguration getConfiguration();

    void setConfiguration(PluginConfiguration configuration);

}
