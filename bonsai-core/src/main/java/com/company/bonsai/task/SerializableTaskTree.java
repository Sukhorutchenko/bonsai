package com.company.bonsai.task;

public class SerializableTaskTree implements TaskConfigurationTree {

    private TaskConfiguration rootTaskConfiguration;
    private FileConfigLoader fileConfigLoader;

    public SerializableTaskTree(FileConfigLoader fileConfigLoader) {
        this.fileConfigLoader = fileConfigLoader;
        loadConfigurationTree();
    }

    @Override
    public TaskConfiguration getRootTaskConfiguration() {
        if (rootTaskConfiguration != null) {
            return rootTaskConfiguration;
        }
        loadConfigurationTree();
        return rootTaskConfiguration;
    }

    public void loadConfigurationTree() {
        rootTaskConfiguration = fileConfigLoader.loadConfigurationTree();
    }

    public void saveConfigurationTree() {
        fileConfigLoader.saveConfigurationTree(rootTaskConfiguration);
    }

}
