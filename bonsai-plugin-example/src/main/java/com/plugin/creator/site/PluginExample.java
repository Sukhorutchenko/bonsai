package com.plugin.creator.site;

import com.company.bonsai.plugin.Inject;
import com.company.bonsai.plugin.EvaluateResource;
import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.task.TaskConfiguration;

@Plugin("PluginName")
@EvaluateResource("lib.js")
public class PluginExample {

    @Inject
    private TaskConfiguration taskConfiguration;

    public String sayHello() {
        return "Example plugin say Hello";
    }

}
