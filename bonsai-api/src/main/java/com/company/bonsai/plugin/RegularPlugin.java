package com.company.bonsai.plugin;

import com.company.bonsai.plugin.config.ConfigurationFieldFactory;
import com.company.bonsai.plugin.config.PluginConfiguration;

import java.io.InputStreamReader;
import java.io.Reader;

public abstract class RegularPlugin implements Plugin {

    @Override
    public Object getFacade() {
        return null;
    }

    @Override
    public Reader getLib() {
        String libsFileName = getLibFileName();
        if (libsFileName != null) {
            ClassLoader classLoader = getClass().getClassLoader();
            return new InputStreamReader(classLoader.getResourceAsStream(libsFileName));
        }
        return null;
    }

    public String getLibFileName() {
        return null;
    }

    @Override
    public PluginConfiguration getConfiguration(ConfigurationFieldFactory fieldFactory) {
        return null;
    }

}