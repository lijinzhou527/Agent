package com.example.demo.AgentUtil;

import com.example.demo.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
@EnableScheduling
public class AgentToolkit {
    private static final Logger LOG = LoggerFactory.getLogger(AgentToolkit.class);

    @Autowired
    Agent agent;

    @Scheduled(cron = "1/5 * * * * *")
    public void runAppInTime() {
        //agent.run();
    }
}
