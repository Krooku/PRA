package third.quartz;
import java.util.Date;
import org.quartz.*;

@PersistJobDataAfterExecution
public class JobWithMap  implements org.quartz.Job
{
    public int minutes_to_end;
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        {
            JobKey key = context.getJobDetail().getKey();

            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            System.out.println(context.getFireTime());

            this.minutes_to_end = -1;
            boolean przerwa = getBreak(context.getFireTime().getHours(), context.getFireTime().getMinutes());

            if(this.minutes_to_end != -1) {
                if (przerwa) {
                    System.out.println(this.minutes_to_end + " minut zostało do końca przerwy.");
                } else {
                    System.out.println(this.minutes_to_end + " minut zostało do końca zajęć.");
                }
            }
            else
                System.out.println("Nie ma aktualnie zajęć.");
        }
    }

    public boolean getBreak(int hours, int minutes)
    {
        boolean przerwa = false;
        switch(hours)
        {
            case 8:
                if(minutes >= 15) {
                    przerwa = false;
                    this.minutes_to_end = 105;
                    this.minutes_to_end -= minutes;
                }
                break;
            case 9:
                if(minutes < 45) {
                    this.minutes_to_end = 45;
                    this.minutes_to_end -= minutes;
                    przerwa = false;
                }
                else
                {
                    this.minutes_to_end = 60;
                    this.minutes_to_end -= minutes;
                    przerwa = true;
                }
                break;
            case 10:
                this.minutes_to_end = 90;
                this.minutes_to_end -= minutes;
                przerwa = false;
                break;
            case 11:
                przerwa = false;
                if(minutes < 30) {
                    this.minutes_to_end = 30;
                    this.minutes_to_end -= minutes;
                }
                else if(minutes >= 45)
                {
                    this.minutes_to_end = 135;
                    this.minutes_to_end -= minutes;
                }
                else
                {
                    this.minutes_to_end = 45;
                    this.minutes_to_end -= minutes;
                    przerwa = true;
                }
                break;
            case 12:
                przerwa = false;
                this.minutes_to_end = 75;
                this.minutes_to_end -= minutes;
                break;
            case 13:
                przerwa = false;
                if(minutes < 15) {
                    this.minutes_to_end = 15;
                    this.minutes_to_end -= minutes;
                }
                else if(minutes >= 45) {
                    this.minutes_to_end = 135;
                    this.minutes_to_end -= minutes;
                }
                else
                {
                    przerwa = true;
                    this.minutes_to_end = 45;
                    this.minutes_to_end -= minutes;
                }
                break;
            case 14:
                przerwa = false;
                this.minutes_to_end = 75;
                this.minutes_to_end -= minutes;
                break;
            case 15:
                przerwa = false;
                if(minutes < 15) {
                    this.minutes_to_end = 15;
                    this.minutes_to_end -= minutes;
                }
                else if(minutes >= 30) {
                    this.minutes_to_end = 120;
                    this.minutes_to_end -= minutes;
                }
                else
                {
                    przerwa = true;
                    this.minutes_to_end = 30;
                    this.minutes_to_end -= minutes;
                }
                break;
            case 16:
                przerwa = false;
                this.minutes_to_end = 60;
                this.minutes_to_end -= minutes;
                break;
            case 17:
                if(minutes < 15) {
                    przerwa = true;
                    this.minutes_to_end = 15;
                    this.minutes_to_end -= minutes;
                }
                else
                {
                    przerwa = false;
                    this.minutes_to_end = 105;
                    this.minutes_to_end -= minutes;
                }
                break;
            case 18:
                if(minutes <= 45)
                {
                    this.minutes_to_end = 45;
                    this.minutes_to_end -= minutes;
                }
                break;
            default:
                this.minutes_to_end = -1;
                break;
        }
        return przerwa;
    }
}
