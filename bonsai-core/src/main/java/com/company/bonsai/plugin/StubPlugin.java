package com.company.bonsai.plugin;

public class StubPlugin implements Plugin {

    private String name;
    private String title;
    private PluginConfiguration configuration;

    public StubPlugin() {
    }

    public StubPlugin(String name) {
        this.name = name;
    }

    public StubPlugin(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public StubPlugin(String name, String title, PluginConfiguration configuration) {
        this.name = name;
        this.title = title;
        this.configuration = configuration;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(PluginConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StubPlugin)) return false;

        StubPlugin that = (StubPlugin) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return !(configuration != null ? !configuration.equals(that.configuration) : that.configuration != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StubPlugin{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", configuration=" + configuration +
                '}';
    }

}
