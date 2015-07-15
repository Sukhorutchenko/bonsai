package com.company.bonsai.script;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

public class StubScript implements Script {

    private String name;
    private String title;
    private ScriptConfiguration configuration;

    public StubScript() {
    }

    public StubScript(String name) {
        this.name = name;
    }

    public StubScript(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public StubScript(String name, String title, ScriptConfiguration configuration) {
        this.name = name;
        this.title = title;
        this.configuration = configuration;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Reader getScriptBody() {
        return new BufferedReader(new StringReader("print('JavaScript code!'); log.info('JavaScript log!');"));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ScriptConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(ScriptConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StubScript)) return false;

        StubScript that = (StubScript) o;

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
        return "StubScript{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", configuration=" + configuration +
                '}';
    }

}
