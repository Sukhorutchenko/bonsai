package com.company.bonsai.interfaces.script;

public interface Script {

    String getTitle();

    String getName();

    ScriptConfiguration getConfiguration();

    void setConfiguration(ScriptConfiguration configuration);

}
