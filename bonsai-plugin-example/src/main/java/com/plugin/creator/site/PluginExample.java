package com.plugin.creator.site;

import com.company.bonsai.plugin.Configuration;
import com.company.bonsai.plugin.Inject;
import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.task.TaskConfiguration;

@Plugin(name = "PluginExample", libs = "lib.js")
public class PluginExample {

    @Inject
    private TaskConfiguration taskConfiguration;

    @Configuration
    private ConfigExample configExample;

    public String sayHello() {
        return "Example plugin say Hello";
    }

    public TaskConfiguration getTaskConfiguration() {
        return taskConfiguration;
    }

    public ConfigExample getConfigExample() {
        return configExample;
    }
}
