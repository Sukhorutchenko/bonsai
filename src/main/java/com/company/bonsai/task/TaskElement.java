package my.task;

public interface TaskElement {

    String getTitle();

    String getName();

    TaskConfiguration getConfiguration();

    void setConfiguration(TaskConfiguration configuration);

}
