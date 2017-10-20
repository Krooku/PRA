package third.quartz;

import org.quartz.*;

@PersistJobDataAfterExecution
public class JobWithMap  implements org.quartz.Job{

    public void execute(JobExecutionContext context) throws JobExecutionException {
        {
            JobKey key = context.getJobDetail().getKey();

            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            //read values
            String str = "";
            String jobSays = dataMap.getString("jobSays");
            int counter = 0;

            if (dataMap.containsKey("counter")) {
                counter = dataMap.getInt("counter");
            }
            if (dataMap.containsKey("str")) {
                str = dataMap.getString("str");
            }
            str += counter;



            System.err.println("Instance " + key + " of says: " + jobSays + " number " + counter + " zadanie " + str);

            counter++;
            //increment and save counter
            dataMap.put("counter", counter);
            dataMap.put("str", str);
        }
    }
}
