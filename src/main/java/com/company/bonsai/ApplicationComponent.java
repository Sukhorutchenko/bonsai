package com.company.bonsai;

public abstract class ApplicationComponent {

    private ApplicationContext applicationContext;

    public ApplicationComponent(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ComponentLocator getComponentLocator() {
        return applicationContext.getComponentLocator();
    }

}
