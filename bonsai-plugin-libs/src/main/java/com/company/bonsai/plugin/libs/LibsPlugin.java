package com.company.bonsai.plugin.libs;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginConfiguration;

public class LibsPlugin implements Plugin {

    private static final String PLUGIN_NAME = "libs";

    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public Object getFacade() {
        return new LibsPluginFacade();
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return null;
    }

    @Override
    public void setConfiguration(PluginConfiguration configuration) {

    }

}
