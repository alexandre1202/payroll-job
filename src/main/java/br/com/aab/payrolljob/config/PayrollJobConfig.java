package br.com.aab.payrolljob.config;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;

@Configuration
public class PayrollJobConfig {

    private static final Logger log = LoggerFactory.getLogger(PayrollJobConfig.class);

    @Bean
    public Tasklet csvTasklet() {
        log.info("It has started!");
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/csv/payroll.csv"))) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    String name = line[0] == null ? "Unknow person" : line[0];
                    int salary = line[1] == null ? 0 : Integer.parseInt(line[1].trim());
                    int discount = line[2] == null ? 0 : Integer.parseInt(line[2].trim());
                    int bonus = line[3] == null ? 0 : Integer.parseInt(line[3].trim());

                    log.info("\n{} with salary of {} discount of {} bonus {}", name, salary, discount, bonus);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return RepeatStatus.FINISHED;
        };
    }
}
