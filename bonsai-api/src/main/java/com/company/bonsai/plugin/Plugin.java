package com.company.bonsai.plugin;

import java.io.Reader;

public interface Plugin {

    String PLUGIN_EXTENSION = ".plugin";

    String getName();

    Object getFacade();

    Reader getLib();

}
