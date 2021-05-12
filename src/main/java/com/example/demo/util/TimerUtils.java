package com.example.demo.util;

import com.example.demo.info.TimerInfo;

import org.quartz.*;
import java.util.Date;
// Write detailed note jobdetail, JobDataMap, JobBuilder

public final class TimerUtils {
    private TimerUtils() {}

    public static JobDetail buildJobDetail(final Class jobClass, final TimerInfo info) {
        final JobDataMap jobDataMap = new JobDataMap();
        // Sets job data
        jobDataMap.put(jobClass.getSimpleName(), info);

        // String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        return JobBuilder
                .newJob(jobClass)
                // .withIdentity(jobClass.getSimpleName()+timeStamp) // Use this to assign unique identifier to job(name+group)
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final TimerInfo info) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.getRepeatInterval());

        if (info.isRunForever()) {
            builder = builder.repeatForever();
        } else {
            // Supports scenario where say n number of scheduling required
            builder = builder.withRepeatCount(info.getTotalFireCount()-1);
        }

        // Trigger trigger = newTrigger() 
        //      .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
        //      .withSchedule(simpleSchedule()
        //          .withIntervalInHours(1)
        //          .repeatForever())
        //      .startAt(futureDate(10, MINUTES))
        //      .build();

        return TriggerBuilder
                .newTrigger()
                // .withIdentity(jobClass.getSimpleName()) // Use this to assign unique identifier to trigger(name+group)
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis()+info.getInitialOffset())).build();
    }
}