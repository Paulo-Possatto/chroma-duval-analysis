package com.chromamon.analysis.duval.controller;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duval-analysis")
@AllArgsConstructor
public class DuvalAnalysisController {

    private final JobLauncher jobLauncher;
    private final Job duvalAnalysisJob;

    @PostMapping("/start")
    public ResponseEntity<String> startAnalysisJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startTime", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(duvalAnalysisJob, jobParameters);
            return ResponseEntity.ok("Job Started!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Job failed: " + e.getMessage());
        }
    }
}