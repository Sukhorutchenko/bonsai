package com.company.bonsai.script;

import com.company.bonsai.ApplicationContainerImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScriptExecutor {

    ApplicationContainerImpl applicationContainerImpl;

    ExecutorService service = Executors.newCachedThreadPool();

    public Future execute(TaskImpl runner) {
        return service.submit(runner);
    }

}
