# Alexandre's project

### Payroll application for employees

* Demo application using Spring Batch.
* The objective here is investigate how to make this simple Spring Batch application works without a dataSource configuration.
* But be in mind that the annotation @EnableBatchProcessing use a dataSource and show the following message when we start the application.

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
...
```
* When I add the data source (src/main/java/br/com/aab/payrolljob/config/DataSourceConfig.java) the application start but the Tasklet stop to work.


### Dependencies

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/gradle-plugin/packaging-oci-image.html)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#howto.batch)

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)

### Summary

#### This repos contains an example of Java 17 application with Spring Boot 3.3.4, Gradle, SLF4J Lombok

### How to build it

```
$ ./gradlew clean install
$ ./gradlew bootRun
```


