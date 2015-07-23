package com.plugin.creator.site;

import com.company.bonsai.plugin.configuration.TextField;

import java.io.Serializable;

public class ConfigExample implements Serializable {

    @TextField
    public String color;

    @TextField
    public String style;

    @TextField
    private String width;

    @TextField
    private String italic;

    @TextField
    private String bold;

    @Override
    public String toString() {
        return "ConfigExample{" +
                "color='" + color + '\'' +
                ", style='" + style + '\'' +
                ", width='" + width + '\'' +
                ", italic='" + italic + '\'' +
                ", bold='" + bold + '\'' +
                '}';
    }

}
