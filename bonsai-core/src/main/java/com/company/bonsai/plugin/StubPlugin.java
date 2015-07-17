package com.company.bonsai.plugin;

public class StubPlugin implements Plugin {

    private String name;
    private PluginConfiguration configuration;

    public StubPlugin() {
    }

    public StubPlugin(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class getFacade() {
        return null;
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(PluginConfiguration configuration) {
        this.configuration = configuration;
    }

}
