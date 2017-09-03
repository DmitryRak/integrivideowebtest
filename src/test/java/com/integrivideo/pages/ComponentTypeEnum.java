package com.integrivideo.pages;

/**
 * Created by drak on 7/19/2017.
 */
public enum ComponentTypeEnum {
    VIDEO_CHAT("Video Chat"),
    CLOUD_VIDEO_RECORDER("Cloud video recorder"),
    MULTI_DEVICE_VIDEO_PLAYER("Multi-device Video Player");

    private final String name;

    ComponentTypeEnum(String value) {
        name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
