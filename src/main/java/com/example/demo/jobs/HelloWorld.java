package com.example.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobDataMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.info.TimerInfo;

public class HelloWorld implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorld.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        // jobDataMap.getJ
        TimerInfo info = (TimerInfo) jobDataMap.get(HelloWorld.class.getSimpleName());
        LOG.info("HELLO WORLD");
        LOG.info(info.getCallbackData());
    }
}