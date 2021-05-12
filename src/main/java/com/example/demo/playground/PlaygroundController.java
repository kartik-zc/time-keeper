package com.example.demo.playground;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/schedule")
public class PlaygroundController {
    private PlaygroundService playgroundService;

    @Autowired
    public PlaygroundController(PlaygroundService service) {
        this.playgroundService = service;
    }

    @PostMapping("/newjob")
    public void runHelloWorldJob(@RequestBody String jsonBody) {
        // parse data from POST api
        // outbound queue, event meta data
        System.out.println(jsonBody);
        this.playgroundService.runHelloWorldJob(jsonBody);
    }
}