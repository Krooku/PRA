package third.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.IOException;

public class JobMapSchedulerSql {

    public static void main(String[] args) throws InterruptedException, IOException {

        //tworzenie pliku
        File plik = new File("odp.txt");
        if(!plik.exists())
        {
            plik.createNewFile();
        }
        else
        {
            int  i = 1;
            plik = new File("odp" + i + ".txt");
            if(plik.exists()) {
                while (plik.exists()) {
                    i++;
                    plik = new File("odp" + i + ".txt");
                    if (!plik.exists()) {
                        plik.createNewFile();
                        break;
                    }
                }
            }
            else
            {
                plik.createNewFile();
            }
        }
        ////////////////////////////////////////////

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(JobWithMapSql.class)
                    .withIdentity("myJob", "group1") // name "myJob", group "group1"
                    .usingJobData("lista", "")
                    .usingJobData("plik", plik.getPath())
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/30 * * ? * *"))
                    .build();


            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            // and start it off
            scheduler.start();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
