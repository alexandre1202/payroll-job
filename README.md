# Spring Boot
* Java 17
* Spring Boot 3.3.4
* Spring Batch 3.3.4
* Gradlew
* SLF4J
* H2 DB

# Hypothetical Payroll application for employees 

* The objective here is providing good and real cases examples of implementations, configurations, deploying and unit tests with Spring Batch. So it is a permanent WIP application.

## Questions and Answers
1) dataSource:

   Q: Why this simple Spring Batch application complain about the dataSource configuration, like we can see in the following log?

   A: Keep in be in mind that the annotation @EnableBatchProcessing uses a dataSource and shows the following message when we start the application.

```
2024-10-11T09:04:48.838-03:00  WARN 69057 --- [payroll-job] [           main] s.c.a.AnnotationConfigApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jobRepository': Cannot resolve reference to bean 'dataSource' while setting bean property 'dataSource'
2024-10-11T09:04:48.844-03:00  INFO 69057 --- [payroll-job] [           main] .s.b.a.l.ConditionEvaluationReportLogger :

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2024-10-11T09:04:48.855-03:00 ERROR 69057 --- [payroll-job] [           main] o.s.b.d.LoggingFailureAnalysisReporter   :

***************************
APPLICATION FAILED TO START
***************************

Description:

A component required a bean named 'dataSource' that could not be found.
```

When I add the data source (src/main/java/br/com/aab/payrolljob/config/DataSourceConfig.java) the application start but the Tasklet stop to work.

## TODO list
* Implement external configuration
* Implement Unit Tests with Mockito, TestNG and maybe Spring Actuator
* Logging
* Externalization of datasources and other properties

## Environment Variables
* payroll.properties.path -> path and property file name. i.e. `payroll.properties.path="/etc/config/applications/payroll/application.properties"`
* payrool.configuration.log -> it is a Log4j configuration. i.e. `payroll.configuration.log=/etc/config/applications/payroll/log4j.xml`

## Data Source configuration - application.properties
Example:
```
batch.datasource.jdbcUrl=jdbc:mysql://localhost:3306/spring_batch?useUnicode=true?characterEncoding=UTF-8?useTimezone=true&serverTimezone=UTC
batch.datasource.username=root
batch.datasource.password=aabPwd

payroll.datasource.jdbcUrl=jdbc:mysql://localhost:3306/app
payroll.datasource.username=root
payroll.datasource.password=aabPwd

spring.batch.initialize-schema=alw
```
### Dependencies

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/gradle-plugin/packaging-oci-image.html)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#howto.batch)
* [TestNG ](https://testng.org/)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Log4j](https://logging.apache.org/log4j/2.x/javadoc.html)

### Summary

#### This repos contains an example of Java 17 application with Spring Boot 3.3.4, Gradle, SLF4J (instead of Logback)

### How to build it

```
$ ./gradlew clean install
$ ./gradlew bootRun
```


