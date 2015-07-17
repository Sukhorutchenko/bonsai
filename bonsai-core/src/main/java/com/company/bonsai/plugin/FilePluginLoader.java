package com.company.bonsai.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
        File[] pluginJarFiles = pluginsDirectory.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
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
                //resolve plugin class here
                Class clazz = classLoader.loadClass("com.company.bonsai.plugin.libs.LibsPlugin");
                pluginClasses.add(clazz);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pluginClasses;
    }

//    public Collection<Plugin> loadPlugins() {
//        loadPluginsClasses(getPluginJars());
//
//
//        return null;
//
//    }
//
//    private Collection<File> getPluginJars() { //maybe need to separate to JarLoader may be useful for loading script's jars
//        File pluginDir = new File(PATH_TO_PLUGINS);
//        File[] files = pluginDir.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
//        return Arrays.asList(files);
//    }
//
//    private Collection<Class>  loadPluginsClasses(Collection<File> jars) {
//        Collection<Class> pluginClasses = new ArrayList<>();
//
//        for (File jarFile : jars) {
//            try {
//                URL jarURL = jarFile.toURI().toURL();
//                JarClassLoader classLoader = new JarClassLoader(jarURL);
//                //resolve plugin class here
//                Class clazz = classLoader.loadClass("com.juriy.hello.HelloPlugin");
//                pluginClasses.add(clazz);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return pluginClasses;
//    }
//
//    private Collection<Plugin> createPlugins(Collection<Class> pluginClasses) {
//        return null;
//    }

}
