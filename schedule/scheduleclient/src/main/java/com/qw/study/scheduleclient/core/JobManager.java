package com.qw.study.scheduleclient.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * @author qunar-qw
 * @date 18-7-13
 */
@Service
public class JobManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobManager.class);

    /**
     * store all job
     */
    private static Map<String, Runnable> jobMap = Maps.newConcurrentMap();

    public static boolean addJob(String jobName, Runnable job) {
        Preconditions.checkNotNull(jobName);
        Preconditions.checkNotNull(job);
        try {
            jobMap.put(jobName, job);
        } catch (Exception e) {
            LOGGER.error("error add job", e);
        }
        return true;
    }

    public static boolean deleteJob(String jobName) {
        Preconditions.checkNotNull(jobName);
        try {
            jobMap.remove(jobName);
        } catch (Exception e) {
            LOGGER.error("error add job", e);
        }
        return true;
    }

    public static boolean runJob(String jobName) {
        Preconditions.checkNotNull(jobName);
        try {
            Runnable currentjob = jobMap.get(jobName);
            currentjob.run();
        } catch (Exception e) {
            LOGGER.error("start job error", e);
            return false;
        }
        return true;
    }


}