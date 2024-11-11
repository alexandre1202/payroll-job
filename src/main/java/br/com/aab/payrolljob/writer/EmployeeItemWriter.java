package br.com.aab.payrolljob.writer;

import br.com.aab.payrolljob.domain.Employee;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeItemWriter {

    @Bean
    public ItemWriter<Employee> flatFileEmployeeItemWriter() {
        return items -> items.forEach(System.out::println);
    }
}
