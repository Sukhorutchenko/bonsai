package com.company.bonsai.task;

import com.company.bonsai.plugin.PluginContainer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

public class FileConfigLoader {

    private File configurationFile;
    private PluginContainer pluginContainer;

    public FileConfigLoader(String configurationFileName, PluginContainer pluginContainer) {
        this.configurationFile = new File(configurationFileName);
        this.pluginContainer = pluginContainer;
    }

    public TaskConfiguration loadConfigurationTree() {
        if (!configurationFile.exists()) {
            return new ScriptEngineTaskConfiguration(TaskConfiguration.ROOT_CONFIGURATION_NAME);
        }
        try (FileInputStream fileInputStream = new FileInputStream(configurationFile);
             ObjectInputStream objectInputStream = new CustomObjectInputStream(fileInputStream)) {
            return (TaskConfiguration) objectInputStream.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            c.printStackTrace();
        }
        return null;
    }

    public void saveConfigurationTree(TaskConfiguration configurationTree) {
        configurationFile.mkdirs();
        if (configurationFile.exists()) {
            configurationFile.delete();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(configurationFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(configurationTree);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class CustomObjectInputStream extends ObjectInputStream {

        public CustomObjectInputStream(InputStream in) throws IOException {
            super(in);
        }

        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            try {
                return super.resolveClass(desc);
            } catch (ClassNotFoundException e) {
                return resolveInPluginScopes(desc);
            }
        }

        private Class resolveInPluginScopes(ObjectStreamClass desc) throws ClassNotFoundException {
            for(Class pluginClass : pluginContainer.getPlugins().values()) {
                try {
                    Class resolvedClass = pluginClass.getClassLoader().loadClass(desc.getName());
                    if (resolvedClass != null) {
                        return resolvedClass;
                    }
                } catch (ClassNotFoundException e) {
                    //ignore. just iterate to next plugin class loader
                }
            }
            throw new ClassNotFoundException();
        }

    }

}
