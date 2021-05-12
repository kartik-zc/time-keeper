package com.example.demo.playground;

import com.example.demo.jobs.HelloWorld;
import com.example.demo.timerService.SchedulerService;
import com.example.demo.info.TimerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaygroundService {
    private final SchedulerService scheduler;

    @Autowired
    public PlaygroundService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloWorldJob(String jsonBody) {
        final TimerInfo info = new TimerInfo();
        // Use event metadata from POST api to set repeat interval
        // For our use case, daily(repeat interval - 24h in ms), hourly(repeat interval - 1h in ms)

        // This is hardcoded, can be populated from jsonBody
        info.setRepeatInterval(2000);
        // info.setTotalFireCount(5);
        
        // Populate outboundqueue in callback data(along with other details)
        info.setCallbackData(jsonBody);

        // Generate random job id, Return generated job id to client, this job id will be used to later remove/update existing job
        this.scheduler.schedule(HelloWorld.class, info);
    }
}