package com.company.bonsai.plugin;

import java.io.Reader;

public interface Plugin {

    String PLUGIN_EXTENSION = ".plugin";

    String getName();

    Object getFacade() throws Exception;

    Reader getLib() throws Exception;

}
