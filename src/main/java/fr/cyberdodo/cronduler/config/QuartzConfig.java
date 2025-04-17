package fr.cyberdodo.cronduler.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfig {

    private final ApplicationContext applicationContext;

    public QuartzConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringBeanJobFactory jobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        // Permet d’injecter les dépendances Spring dans les jobs Quartz
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource ds, SpringBeanJobFactory jobFactory) {
        SchedulerFactoryBean sfb = new SchedulerFactoryBean();
        sfb.setDataSource(ds);
        sfb.setJobFactory(jobFactory);
        Properties props = new Properties();
        props.setProperty("org.quartz.threadPool.threadCount", "10");
        sfb.setQuartzProperties(props);
        sfb.setWaitForJobsToCompleteOnShutdown(true);
        return sfb;
    }
}