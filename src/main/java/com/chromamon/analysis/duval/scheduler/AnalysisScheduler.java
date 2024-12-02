package com.chromamon.analysis.duval.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalysisScheduler {

    public static final int HOURS = 4; // Determine how many hours to start the process
    public static final int MINUTES = 0; // Determine how many minutes to start the process
    public static final int SECONDS = 0; // Determine how many seconds to start the process
    //E.g.: If I want to start the process every 3 hours and 20 minutes, change the variable HOURS for 3 and the MINUTES to 20...

    public static final int TIME_SCHEDULED = HOURS*3600000 + MINUTES*60000 + SECONDS*1000;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job duvalAnalysisJob;

    @Scheduled(fixedRate = TIME_SCHEDULED)
    public void scheduledAnalysisJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startTime", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(duvalAnalysisJob, jobParameters);
            System.out.println("Job started by scheduler.");
        } catch (Exception e) {
            System.err.println("Error starting scheduled job: " + e.getMessage());
        }
    }
}