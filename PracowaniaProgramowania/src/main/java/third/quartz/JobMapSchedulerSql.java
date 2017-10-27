package third.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            /////////////////////////////////////////////////////

            JobDetail job1 = newJob(JobWithMap.class)
                    .withIdentity("myJob", "group2") // name "myJob", group "group1"
                    .usingJobData("jobSays", "Hello World!")
                    .build();

            Trigger trigger1 = newTrigger()
                    .withIdentity("trigger1", "group2")
                    .startNow()
                    .withSchedule(cronSchedule("0 * * ? * MON-FRI"))
                    .build();

            ////////////////////////////////////////////////////

            /*JobDetail job = newJob(JobWithMapSql.class)
                    .withIdentity("myJob", "group1") // name "myJob", group "group1"
                    .usingJobData("lista", "")
                    .usingJobData("plik", plik.getPath())
                    .build();


            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/30 * * ? * *"))
                    .build();*/

            /////////////////////////////////////////////////////



            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job1, trigger1);
            //scheduler.scheduleJob(job, trigger);
            // and start it off
            scheduler.start();
            Scanner in = new Scanner(System.in);
            int nr = 1;
            boolean first = true;
            String lista = "";
            while(nr != 0)
            {
                System.out.print("Podaj nr zadania: ");
                nr = in.nextInt();
                in.nextLine();
                if(nr != 0) {
                    System.out.print("Podaj treść zapytania SQL: ");
                    String sql = in.nextLine();
                    if (isCorrectquerry(sql)) {
                        System.out.print("Dobre zapytanie\n");
                        if (first) {
                            lista = nr + "." + sql;
                        } else {
                            lista += "-" + nr + "." + sql;
                        }
                        first = false;
                    } else {
                        System.out.print("Błędne zapytanie\n");
                        boolean flag = false;
                        while (!flag) {
                            System.out.print("Podaj treść zapytania SQL jeszcze raz: ");
                            sql = in.nextLine();
                            if (isCorrectquerry(sql)) {
                                if (first) {
                                    lista = nr + "." + sql;
                                } else {
                                    lista += "-" + nr + "." + sql;
                                }
                                first = false;
                                System.out.print("Dobre zapytanie\n");
                                flag = true;

                            }
                        }
                    }
                }
            }
            PrintWriter zapis = null;
            try {
                zapis = new PrintWriter(plik.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < sort_queries(lista).size(); i++)
            {
                zapis.println(sort_queries(lista).get(i));
            }
            zapis.close();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
    public static boolean isCorrectquerry(String querry)
    {
        String wynik[] = null;
        wynik = querry.split(" ");
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
    public static List sort_queries(String lista)
    {
        List<String> list = new ArrayList<String>();

        String wynik[] = null;
        wynik = lista.split("-");
        int len = wynik.length;
        for(int i = 0; i < len; i++)
        {

            for(int j = 0; j < len; j++)
            {
                if(i != j && wynik[i].charAt(0) == wynik[j].charAt(0))
                {
                    if(j > i)
                    {
                        wynik[i] = "d";
                    }
                    else
                    {
                        wynik[j] = "d";
                    }
                }

            }

        }
        for(int i = 0; i < len; i++)
        {
            if(wynik[i] != "d")
            {
                list.add(wynik[i]);
            }
        }
        Collections.sort(list);
        return list;
    }
}
