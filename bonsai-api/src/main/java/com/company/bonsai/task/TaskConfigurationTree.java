package com.company.bonsai.task;

public interface TaskConfigurationTree {

    TaskConfiguration getRootTaskConfiguration();

    void loadConfigurationTree();

    void saveConfigurationTree();

}
