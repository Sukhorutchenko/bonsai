package com.company.bonsai.script;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

public class StringScript implements Script {

    private String name;
    private String code = "print('JavaScript code!');";

    public StringScript() {
    }

    public StringScript(String name) {
        this.name = name;
    }

    public StringScript(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public Reader getScriptBody() {
        return new BufferedReader(new StringReader(code));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
