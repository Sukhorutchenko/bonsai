package com.company.bonsai.task;

import java.util.HashMap;
import java.util.Map;

public class MapStorage implements Storage {

    private Map<Object, Object> storageMap = new HashMap<>();

    @Override
    public void set(Object key, Object value) {
        storageMap.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return storageMap.get(key);
    }

}
