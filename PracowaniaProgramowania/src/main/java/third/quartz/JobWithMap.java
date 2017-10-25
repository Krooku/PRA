package third.quartz;

import org.quartz.*;

@PersistJobDataAfterExecution
public class JobWithMap  implements org.quartz.Job{

    public void execute(JobExecutionContext context) throws JobExecutionException {
        {
            JobKey key = context.getJobDetail().getKey();

            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            System.out.println(context.getFireTime());

            int minutes_to_end = -1;
            boolean przerwa = false;
            switch(context.getFireTime().getHours())
            {
                case 8:
                    if(context.getFireTime().getMinutes() >= 15) {
                        przerwa = false;
                        minutes_to_end = 105;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    break;
                case 9:
                    if(context.getFireTime().getMinutes() < 45) {
                        minutes_to_end = 45;
                        minutes_to_end -= context.getFireTime().getMinutes();
                        przerwa = false;
                    }
                    else
                    {
                        minutes_to_end = 60;
                        minutes_to_end -= context.getFireTime().getMinutes();
                        przerwa = true;
                    }
                    break;
                case 10:
                    minutes_to_end = 90;
                    minutes_to_end -= context.getFireTime().getMinutes();
                    przerwa = false;
                    break;
                case 11:
                    przerwa = false;
                    if(context.getFireTime().getMinutes() < 30) {
                        minutes_to_end = 30;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else if(context.getFireTime().getMinutes() >= 45)
                    {
                        minutes_to_end = 135;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else
                    {
                        minutes_to_end = 45;
                        minutes_to_end -= context.getFireTime().getMinutes();
                        przerwa = true;
                    }
                    break;
                case 12:
                    przerwa = false;
                    minutes_to_end = 75;
                    minutes_to_end -= context.getFireTime().getMinutes();
                    break;
                case 13:
                    przerwa = false;
                    if(context.getFireTime().getMinutes() < 15) {
                        minutes_to_end = 15;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else if(context.getFireTime().getMinutes() >= 45) {
                        minutes_to_end = 135;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else
                    {
                        przerwa = true;
                        minutes_to_end = 45;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    break;
                case 14:
                    przerwa = false;
                    minutes_to_end = 75;
                    minutes_to_end -= context.getFireTime().getMinutes();
                    break;
                case 15:
                    przerwa = false;
                    if(context.getFireTime().getMinutes() < 15) {
                        minutes_to_end = 15;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else if(context.getFireTime().getMinutes() >= 30) {
                        minutes_to_end = 120;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else
                    {
                        przerwa = true;
                        minutes_to_end = 30;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    break;
                case 16:
                    przerwa = false;
                    minutes_to_end = 60;
                    minutes_to_end -= context.getFireTime().getMinutes();
                    break;
                case 17:
                    if(context.getFireTime().getMinutes() < 15) {
                        przerwa = true;
                        minutes_to_end = 15;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    else
                    {
                        przerwa = false;
                        minutes_to_end = 105;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    break;
                case 18:
                    if(context.getFireTime().getMinutes() <= 45)
                    {
                        minutes_to_end = 45;
                        minutes_to_end -= context.getFireTime().getMinutes();
                    }
                    break;
                default:
                    minutes_to_end = -1;
                    break;
            }
            if(minutes_to_end != -1) {
                if (przerwa) {
                    System.out.println(minutes_to_end + " minut zostało do końca przerwy.");
                } else {
                    System.out.println(minutes_to_end + " minut zostało do końca zajęć.");
                }
            }
            else
                System.out.println("Nie ma aktualnie zajęć.");
            //read values
            /*String str = "";
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
            dataMap.put("str", str);*/
        }
    }
}
