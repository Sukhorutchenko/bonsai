package com.company.bonsai.plugin.libs;

import com.company.bonsai.plugin.config.ConfigurationField;
import com.company.bonsai.plugin.config.PluginConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LibsConfiguration implements PluginConfiguration {

    private List<ConfigurationField> fields = new ArrayList<>();

    @Override
    public List<ConfigurationField> getFields() {
        return fields;
    }

}
