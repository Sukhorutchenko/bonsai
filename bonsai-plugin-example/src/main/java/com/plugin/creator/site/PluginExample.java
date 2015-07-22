package com.plugin.creator.site;

import com.company.bonsai.plugin.Inject;
import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.task.TaskConfiguration;

@Plugin(name = "PluginName", configuration = ConfigExample.class, libs = "lib.js")
public class PluginExample {

    @Inject
    private TaskConfiguration taskConfiguration;

    public String sayHello() {
        return "Example plugin say Hello";
    }

}
