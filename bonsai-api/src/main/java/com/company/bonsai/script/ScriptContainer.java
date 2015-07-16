package com.company.bonsai.script;

import java.util.List;

public interface ScriptContainer {

    Script getScriptByName(String name);

    List<Script> getScripts();

}
