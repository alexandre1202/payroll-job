package br.com.aab.payrolljob.steps;

import br.com.aab.payrolljob.domain.Employee;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EmployeeFlatFileStep {


    @Bean
    public Step employeeStep(
            ItemReader<Employee> flatFileEmployeeItemReader,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("employeeStepBuilder", jobRepository)
                .<Employee, Employee>chunk(2, transactionManager)
                .reader(flatFileEmployeeItemReader)
                .build();

    }
}
