package com.company.bonsai.plugin.libs;

import com.company.bonsai.plugin.Plugin;

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

}
