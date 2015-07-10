package my.task;

public interface Task extends Runnable, TaskElement {

    TaskExecutionInfo getExecutionInfo();

}
