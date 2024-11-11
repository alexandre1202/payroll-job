package br.com.aab.payrolljob.reader;

import br.com.aab.payrolljob.domain.Employee;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class EmployeeItemReader {

    @Bean
    @StepScope
    public FlatFileItemReader<Employee> flatFileEmployeeItemReader(
            @Value("${jobParameters['employeeFiles']}") Resource employeeFilesResource) {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("flatFileEmployeeItemReader")
                .resource(employeeFilesResource)
                .fixedLength()
                .columns(new Range[] {new Range(1,53), new Range(54,18), new Range(73, 6), new Range(81, 18)})
                .names("name", "salary", "discount", "bonus")
                .targetType(Employee.class)
                .build();
    }
}
