package com.company.bonsai.plugin.config;

public interface ConfigurationFieldFactory {

    BooleanConfigurationField createBoolean();

    StringConfigurationField createString();

    SelectConfigurationField createSelect();

}
