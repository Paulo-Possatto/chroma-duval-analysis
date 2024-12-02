package com.chromamon.analysis.duval.config;

import com.chromamon.analysis.duval.model.AnalysisData;
import com.chromamon.analysis.duval.model.DiagnosticData;
import com.chromamon.analysis.duval.repository.DiagnosticRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class DuvalBatchConfig {

    private DiagnosticRepository diagnosticRepository;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job duvalAnalysisJob(Step duvalAnalysisStep) {
        return new JobBuilder("duvalAnalysisJob", jobRepository)
                .start(duvalAnalysisStep)
                .build();
    }

    @Bean
    public Step duvalAnalysisStep(ItemReader<AnalysisData> reader,
                                  ItemProcessor<AnalysisData, DiagnosticData> processor,
                                  ItemWriter<DiagnosticData> writer) {
        return new StepBuilder("duvalAnalysisStep", jobRepository)
                .<AnalysisData, DiagnosticData>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}