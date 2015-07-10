package my.task;

public interface TaskExecutionInfo {

    TaskExecutionState getState();

    void setState(TaskExecutionState state);

}
