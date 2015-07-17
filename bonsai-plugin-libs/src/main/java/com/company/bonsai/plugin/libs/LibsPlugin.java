package com.company.bonsai.plugin.libs;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginConfiguration;

public class LibsPlugin implements Plugin, LibsPluginFacade {

    private static final String PLUGIN_NAME = "libs";

    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public Class getFacade() {
        return LibsPluginFacade.class;
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return null;
    }

    @Override
    public void setConfiguration(PluginConfiguration configuration) {

    }

    @Override
    public String sayHello() {
        return "Libs Plugin Hello string";
    }

}
