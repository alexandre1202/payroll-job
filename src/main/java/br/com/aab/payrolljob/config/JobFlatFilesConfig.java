package br.com.aab.payrolljob.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class JobFlatFilesConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobFlatFilesConfig.class);
    private final JobRepository jobRepository;

    public JobFlatFilesConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job job(Step employeeStep) {
        LOGGER.info("Creating job for reading employees flat files");
        return new JobBuilder("flatFilesForEmployeeJob", jobRepository)
                .start(employeeStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }


}
