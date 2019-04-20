package com.qw.study.scheduleclient.management;

import com.qw.study.scheduleclient.api.EScheduleTask;
import com.qw.study.scheduleclient.core.JobManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * trigger project task
 * @see EScheduleTask
 * @author qunar-qw
 * @date 18-7-13
 */
@Controller
@RequestMapping(value = "eschdule-client")
public class TriggerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriggerController.class);

    @RequestMapping("start")
    public ResponseVo start(String jobName) {
        try {
            JobManager.runJob(jobName);
        } catch (Exception e) {
            LOGGER.error("start job: {} exception", jobName, e);
            return ResponseVo.createTrue("false");
        }
        return ResponseVo.createTrue("success");
    }



}