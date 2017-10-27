package third.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;


import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.*;

public class JobMapScheduler {

    public static void main(String[] args) throws InterruptedException {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = newJob(JobWithMap.class)
                    .withIdentity("myJob", "group1") // name "myJob", group "group1"
                    .usingJobData("jobSays", "Hello World!")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0 * * ? * MON-FRI"))
                    .build();


            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // and start it off
            scheduler.start();


            //Thread.sleep(10000);

            //scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
