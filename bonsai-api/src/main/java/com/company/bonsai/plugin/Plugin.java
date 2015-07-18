package com.company.bonsai.plugin;

public interface Plugin {

    String PLUGIN_EXTENSION = ".plugin";

    String getName();

    Object getFacade();

}
