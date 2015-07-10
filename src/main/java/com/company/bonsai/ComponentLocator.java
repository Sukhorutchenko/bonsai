package com.company.bonsai;

public interface ComponentLocator {

    ApplicationComponent lookupComponent(Class clazz);

    void registerComponent(ApplicationComponent component);

}
