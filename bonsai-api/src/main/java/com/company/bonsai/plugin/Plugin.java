package com.company.bonsai.plugin;

import com.company.bonsai.plugin.config.ConfigurationFieldFactory;
import com.company.bonsai.plugin.config.PluginConfiguration;

import java.io.Reader;

public interface Plugin {

    String PLUGIN_EXTENSION = ".plugin";

    String getName();

    Object getFacade();

    Reader getLib();

    PluginConfiguration getConfiguration(ConfigurationFieldFactory fieldFactory);

}
