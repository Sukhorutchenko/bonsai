package com.company.bonsai.task;

public interface Task extends Runnable {

    String ARGUMENT_EQUAL = "=";

    TaskConfiguration getTaskConfiguration();

}
