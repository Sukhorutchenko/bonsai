package com.company.bonsai.script;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileScriptLoader {

    private File scriptsDirectory;

    public FileScriptLoader(String scriptsDirectoryPath) {
        File scriptsDirectory =  new File(scriptsDirectoryPath);
        if (!scriptsDirectory.isDirectory()) {
            throw new IllegalArgumentException("argument must be a directory, not a file");
        }
        this.scriptsDirectory = scriptsDirectory;
    }

    public List<Script> loadAllScripts() {
        List<Script> scripts = new ArrayList<>();
        File[] scriptFiles = scriptsDirectory.listFiles(file -> file.isFile() && file.getName().endsWith(".js"));
        for(File scriptFile : scriptFiles) {
            try {
                Script script = loadScript(scriptFile);
                scripts.add(script);
            } catch (FileNotFoundException e) {
                e.printStackTrace(); //TODO
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return scripts;
    }

    private Script loadScript(File scriptFile) throws IOException {
        byte[] fileBytes = new byte[(int) scriptFile.length()];
        new FileInputStream(scriptFile).read(fileBytes);
        return new MemoryScript(scriptFile.getName(), fileBytes);
    }

}
