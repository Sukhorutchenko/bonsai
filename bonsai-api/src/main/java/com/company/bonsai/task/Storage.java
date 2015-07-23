package com.company.bonsai.task;

import java.io.Serializable;

public interface Storage extends Serializable {

    void set(Object key, Object value);

    Object get(Object key);

}
