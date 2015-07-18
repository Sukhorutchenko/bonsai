package com.company.bonsai.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    public List<Plugin> loadAllPlugins() {
        List<Plugin> plugins = new ArrayList<>();
        File[] pluginJarFiles = pluginsDirectory.listFiles(file -> file.isFile() && file.getName().endsWith(Plugin.PLUGIN_EXTENSION));
        List<Class> pluginClasses = loadPluginClasses(pluginJarFiles);
        for (Class clazz : pluginClasses) {
            try {
                Plugin plugin = (Plugin) clazz.newInstance();
                plugins.add(plugin);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return plugins;
    }

    private List<Class> loadPluginClasses(File[] pluginJarFiles) {
        List<Class> pluginClasses = new ArrayList<>();
        for(File pluginJarFile : pluginJarFiles) {
            try {
                URL jarURL = pluginJarFile.toURI().toURL();
                JarClassLoader classLoader = new JarClassLoader(jarURL);
                Class clazz = classLoader.loadClass(classLoader.getMainClassName());
                pluginClasses.add(clazz);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pluginClasses;
    }

}
