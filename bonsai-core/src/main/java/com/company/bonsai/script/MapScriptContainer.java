package com.company.bonsai.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Script> getScripts() {
        return new ArrayList<>(scripts.values());
    }

    private void initScripts(Map<String, Script> scripts) {
        Script stubScript = new StubScript("StubScript");
        scripts.put(stubScript.getName(), stubScript);
    }

}
