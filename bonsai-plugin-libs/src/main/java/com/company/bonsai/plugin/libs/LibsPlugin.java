package com.company.bonsai.plugin.libs;

import com.company.bonsai.plugin.RegularPlugin;

public class LibsPlugin extends RegularPlugin {

    private static final String PLUGIN_NAME = "libs";
    private static final String LIB_FILE_NAME = "lib.js";

    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public Object getFacade() {
        return new LibsPluginFacade();
    }

    @Override
    public String getLibFileName() {
        return LIB_FILE_NAME;
    }

}
