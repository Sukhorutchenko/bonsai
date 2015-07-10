package com.company.bonsai.script;

public interface Script {

    String getTitle();

    String getName();

    ScriptConfiguration getConfiguration();

    void setConfiguration(ScriptConfiguration configuration);

}
