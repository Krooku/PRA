package third.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class JobMapSchedulerSql {

    public static void main(String[] args) throws InterruptedException, IOException {

        //tworzenie pliku
        int  i = 0;
        File plik = new File("odp.txt");
        while (plik.exists())
        {
            i++;
            plik = new File("odp" + i + ".txt");
        }

        plik.createNewFile();
        ////////////////////////////////////////////

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            /////////////////////////////////////////////////////

            JobDetail job1 = newJob(JobWithMap.class)
                    .withIdentity("myJob", "group1") // name "myJob", group "group1"
                    .build();

            Trigger trigger1 = newTrigger()
                    .startNow()
                    .withSchedule(cronSchedule("0 * * ? * MON-FRI"))
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job1, trigger1);
            // and start it off
            scheduler.start();

            //pobieranie zapytan od uzytkownika i dodanie ich do stringa
            Scanner in = new Scanner(System.in);
            int nr;
            boolean first = true;
            String lista = "";
            System.out.print("Podaj nr zadania: ");

            //po podaniu numeru zadanie 0 wychodzi z petli zbierajacej tresci zapytan
            while((nr = in.nextInt()) != 0)
            {
                in.nextLine();
                System.out.print("Podaj treść zapytania SQL: ");
                String sql = in.nextLine();
                if (isCorrectquery(sql))
                {
                    System.out.print("Dobre zapytanie\n");
                    if (first) {
                        lista = nr + "." + sql;
                    } else {
                        lista += "-" + nr + "." + sql;
                    }
                    first = false;
                }
                else
                {
                    System.out.print("Błędne zapytanie\n");
                    boolean flag = false;
                    while (!flag)
                    {
                        System.out.print("Podaj treść zapytania SQL jeszcze raz: ");
                        sql = in.nextLine();
                        if (isCorrectquery(sql))
                        {
                            if (first)
                            {
                                lista = nr + "." + sql;
                            }
                            else
                            {
                                lista += "-" + nr + "." + sql;
                            }
                            first = false;
                            System.out.print("Dobre zapytanie\n");
                            flag = true;

                        }
                    }
                }
                System.out.print("Podaj nr zadania: ");
                /*JobDetail job2 = newJob(JobWithMapSql.class)
                        .usingJobData("lista", lista)
                        .usingJobData("path", plik.getPath())
                        .build();
                Trigger trigger2 = newTrigger()
                        .startNow()
                        .withSchedule(cronSchedule("0/30 * * ? * *"))
                        .build();

                scheduler.scheduleJob(job2, trigger2);
                scheduler.start();*/
            }

            ////////////////////////////////////////////////////
            JobDetail job2 = newJob(JobWithMapSql.class)
                    .usingJobData("lista", lista)
                    .usingJobData("path", plik.getPath())
                    .build();
            Trigger trigger2 = newTrigger()
                    .startNow()
                    .withSchedule(cronSchedule("0/30 * * ? * *"))
                    .build();

            scheduler.scheduleJob(job2, trigger2);
            scheduler.start();

            //////////////////////////////////////////////////////////////////////////////////////

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
    public static boolean isCorrectquery(String query)
    {
        String wynik[] = null;
        wynik = query.split(" ");
        int len = wynik.length;

        int prev = 0;

        if(!wynik[0].equalsIgnoreCase("select"))
            return false;
        for(int i = 1; i < len; i++)
        {
            if(wynik[i].equalsIgnoreCase("from") || wynik[i].equalsIgnoreCase("where") || wynik[i].equalsIgnoreCase("order"))
            {
                if(wynik[i].equalsIgnoreCase("from") && prev == 0)
                {
                    prev++;
                }
                else if(wynik[i].equalsIgnoreCase("where") && prev == 1)
                {
                    prev++;
                }
                else if(wynik[i].equalsIgnoreCase("order") && (prev == 2 || prev == 1))
                {
                    if(i != len-1 && wynik[i+1].equalsIgnoreCase("by"))
                    {
                        prev++;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
        }
        return true;
    }
}
