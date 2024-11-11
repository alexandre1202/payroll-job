package br.com.aab.payrolljob.config;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.FileReader;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class PayrollJobConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayrollJobConfig.class);
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public PayrollJobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job payrollJob(final Step csvStep) {
        LOGGER.info("Creating job");
        return new JobBuilder("PayRollJob", jobRepository)
                .start(csvStep)
                .build();
    }

    @Bean
    public Step csvStep() {
        return new StepBuilder("csvStep", jobRepository)
                .tasklet(csvTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet csvTasklet() {
        LOGGER.info("Tasklet has started!");
        return (contribution, chunkContext) -> {
            try {
                return readCSVFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return RepeatStatus.CONTINUABLE;
        };
    }

    private static RepeatStatus readCSVFile() {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/csv/payroll.csv"))) {
            printCSVColumns(csvReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return RepeatStatus.FINISHED;
    }


    private static void printCSVColumns(CSVReader csvReader) throws IOException, CsvValidationException {
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            String name = line[0] == null ? "Unknow person" : line[0];
            int salary = line[1] == null ? 0 : Integer.parseInt(line[1].trim());
            int discount = line[2] == null ? 0 : Integer.parseInt(line[2].trim());
            int bonus = line[3] == null ? 0 : Integer.parseInt(line[3].trim());

            LOGGER.info("\n{} with salary of {} discount of {} bonus {}", name, salary, discount, bonus);
        }
    }

}
