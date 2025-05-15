package com.synex.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob")
                .storeDurably()
                .build();
    }

    /*
    @Bean
    public Trigger jobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  // every 10 seconds
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("myTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
    */
    
    
    
    @Bean
    public Trigger jobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("0 6 22 * * ?"); // every 15 seconds

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("myCronTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
    
    

}
