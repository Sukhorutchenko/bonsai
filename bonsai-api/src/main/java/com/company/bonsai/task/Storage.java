package com.company.bonsai.task;

public interface Storage {

    void set(Object key, Object value);

    Object get(Object key);

}
