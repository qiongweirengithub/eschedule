package com.qw.study.scheduleclient.core;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.qw.study.scheduleclient.api.EScheduleTask;
import com.qw.study.scheduleclient.common.ClientAction;
import com.qw.study.scheduleclient.configures.ESheduleConfigure;
import com.qw.study.scheduleclient.utils.EscheduleHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author qunar-qw
 * @date 18-7-13
 */
@Service
public class JobRegisterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobRegisterService.class);

    private ESheduleConfigure eSheduleConfigure = new ESheduleConfigure();

    public boolean registerJob(Method method, Object bean) {

        EScheduleTask eScheduleTask = method.getAnnotation(EScheduleTask.class);

        Map<String, String> jobParamMap = Maps.newHashMap();

        jobParamMap.put("jobName", eScheduleTask.jobName());
        jobParamMap.put("ip", "localhost");
        jobParamMap.put("cron", eScheduleTask.cron());
        jobParamMap.put("group", eScheduleTask.group());
        Joiner joiner  = Joiner.on("/");

        try {
            String url = joiner.join(eSheduleConfigure.serverIp, eSheduleConfigure.serverUrl, ClientAction.REGISTER.action);
            /**
             * register to server
             */
            EscheduleHttpUtils.get(url, jobParamMap);

            /**
             * Runner wrapper
             */
            Runnable job = new ScheduledMethodRunnable(bean, method);
            /**
             * add to jobManagement
             */
            JobManager.addJob(eScheduleTask.jobName(), job);

        } catch (IOException e) {
            LOGGER.error("注册定时任务失败", e);
        }

        return true;
    }

}