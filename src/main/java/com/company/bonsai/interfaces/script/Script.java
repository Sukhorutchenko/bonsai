package com.company.bonsai.interfaces.script;

import java.io.Reader;

public interface Script {

    String getName();

    String getTitle();

    Reader getScriptBody();

    ScriptConfiguration getConfiguration();

    void setConfiguration(ScriptConfiguration configuration);

}
