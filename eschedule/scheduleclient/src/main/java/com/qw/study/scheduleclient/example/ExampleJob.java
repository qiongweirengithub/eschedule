package com.qw.study.scheduleclient.example;

import com.qw.study.scheduleclient.api.EScheduleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * example for use
 * @author qunar-qw
 * @date 18-7-13
 */
@Component
public class ExampleJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleJob.class);

    @EScheduleTask(jobName = "exampleJob", group = "exampleGroup", cron = "0/1 * * * * *")
    public void exampleJob() {
        LOGGER.info("example job run..");
    }
}