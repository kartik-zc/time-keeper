package com.example.demo.info;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// public class TimerInfo implements Serializable {
public class TimerInfo {
    // Seriablizable was added because of error
    // Couldn't store job: Unable to serialize JobDataMap for insertion into database because the value of property 'HelloWorld' is not serializable: com.example.demo.info.TimerInfo
    private int totalFireCount;
    private boolean runForever;
    private long repeatInterval;
    private long initialOffset;
    private String callbackData; // Something that we want to send to our job
}