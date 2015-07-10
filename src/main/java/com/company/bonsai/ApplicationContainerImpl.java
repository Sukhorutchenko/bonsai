package com.company.bonsai;

import com.company.bonsai.plugin.PluginContainerImpl;
import com.company.bonsai.script.ScriptContainerImpl;
import com.company.bonsai.script.ScriptExecutor;
import com.company.bonsai.script.TaskImpl;

import javax.script.ScriptException;
import java.io.FileNotFoundException;

public class ApplicationContainerImpl {

    PluginContainerImpl pluginContainer;
    ScriptContainerImpl scriptContainerImpl;
    ScriptExecutor scriptExecutor;

    TaskImpl runner;


    public ApplicationContainerImpl() {
        this.pluginContainer = new PluginContainerImpl();
        this.scriptContainerImpl = new ScriptContainerImpl();
        this.scriptExecutor = new ScriptExecutor();

        this.runner = new TaskImpl();
    }

    public void start() throws ScriptException, FileNotFoundException {
        runner.setPluginContainer(pluginContainer);
        runner.run();
    }


    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        new ApplicationContainerImpl().start();
    }

}
