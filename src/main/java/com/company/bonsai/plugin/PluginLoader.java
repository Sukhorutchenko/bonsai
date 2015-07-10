package com.company.bonsai.plugin;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PluginLoader {

    private static final String PATH_TO_PLUGINS = "plugin";

    public Collection<Plugin> loadPlugins() {
        loadPluginsClasses(getPluginJars());


        return null;

    }

    private Collection<File> getPluginJars() { //maybe need to separate to JarLoader may be useful for loading script's jars
        File pluginDir = new File(PATH_TO_PLUGINS);
        File[] files = pluginDir.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        return Arrays.asList(files);
    }

    private Collection<Class>  loadPluginsClasses(Collection<File> jars) {
        Collection<Class> pluginClasses = new ArrayList<>();

        for (File jarFile : jars) {
            try {
                URL jarURL = jarFile.toURI().toURL();
                JarClassLoader classLoader = new JarClassLoader(jarURL);
                //resolve plugin class here
                Class clazz = classLoader.loadClass("com.juriy.hello.HelloPlugin");
                pluginClasses.add(clazz);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return pluginClasses;
    }

    private Collection<Plugin> createPlugins(Collection<Class> pluginClasses) {
        return null;
    }

}
