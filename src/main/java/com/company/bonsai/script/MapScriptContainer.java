package com.company.bonsai.script;

import com.company.bonsai.interfaces.script.Script;
import com.company.bonsai.interfaces.script.ScriptContainer;

import java.util.HashMap;
import java.util.Map;

public class MapScriptContainer implements ScriptContainer {

    private final Map<String /*scriptName*/, Script> scripts = new HashMap<>();

    public MapScriptContainer() {
        initScripts(scripts);
    }

    @Override
    public Script getScriptByName(String name) {
        return scripts.get(name);
    }

    public Map<String, Script> getScripts() {
        return scripts;
    }

    private void initScripts(Map<String, Script> scripts) {
        Script stubScript = new StubScript("StubScript");
        scripts.put(stubScript.getName(), stubScript);
    }

}
