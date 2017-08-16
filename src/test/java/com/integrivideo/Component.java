package com.integrivideo;

import com.integrivideo.pages.ComponentTypeEnum;

/**
 * Created by drak on 8/16/2017.
 */
public class Component {

    private String name;
    private ComponentTypeEnum componentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentTypeEnum getComponentType() {
        return componentType;
    }

    public void setComponentTypeEnum(ComponentTypeEnum componentType) {
        this.componentType = componentType;
    }
}
