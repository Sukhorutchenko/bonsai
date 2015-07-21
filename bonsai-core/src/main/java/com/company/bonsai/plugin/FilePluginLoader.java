package com.company.bonsai.plugin;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilePluginLoader {

    private static final Logger LOG = LoggerFactory.getLogger(FilePluginLoader.class);
    private File pluginsDirectory;

    public FilePluginLoader(String pluginsDirectoryPath) {
        File pluginsDirectory =  new File(pluginsDirectoryPath);
        if (!pluginsDirectory.isDirectory()) {
            throw new IllegalArgumentException("argument must be a directory, not a file");
        }
        this.pluginsDirectory = pluginsDirectory;
    }

    public List<Class> loadPluginClasses() {
        List<Class> pluginClasses = new ArrayList<>();
        for (File pluginJar : getPluginsJars()) {
            pluginClasses.addAll(getPluginAnnotatedClasses(pluginJar));
        }
        return pluginClasses;
    }

    private File[] getPluginsJars() {
        return pluginsDirectory.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
    }

    private Set<Class<?>> getPluginAnnotatedClasses(File pluginJar) {
        URL jarURL = null;
        try {
            jarURL = pluginJar.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLClassLoader classLoader = new URLClassLoader(new URL[] { jarURL }, getClass().getClassLoader());
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .addClassLoader(classLoader)
                .setUrls(classLoader.getURLs()));
        return reflections.getTypesAnnotatedWith(Plugin.class);
    }

}
