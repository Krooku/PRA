package third.quartz;
import org.quartz.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@PersistJobDataAfterExecution
public class JobWithMapSql implements Job
{
    public int prev_index;

    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        List<String> lista = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            String path = dataMap.getString("plik");

            File plik = new File(path);

            String prev_sql = dataMap.getString("lista");

            System.out.print("Podaj nr zadania: ");
            int nr = in.nextInt();
            in.nextLine();
            System.out.print("Podaj treść zapytania SQL: ");
            String sql = in.nextLine();
            if(isCorrectquerry(sql))
            {
                System.out.print("Dobre zapytanie\n");
            }
            else
            {
                System.out.print("Błędne zapytanie\n");
                boolean flag = false;
                while(!flag)
                {
                    System.out.print("Podaj treść zapytania SQL jeszcze raz: ");
                    String sql1 = in.nextLine();
                    if(isCorrectquerry(sql1))
                    {
                        System.out.print("Dobre zapytanie\n");
                        flag = true;

                    }
                }
            }
            if(prev_sql == "")
                dataMap.put("lista", nr + "." + sql);
            else
                dataMap.put("lista", prev_sql + "-" + nr + "." + sql);

            PrintWriter zapis = null;
            try {
                zapis = new PrintWriter(plik.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(sort_queries(dataMap.getString("lista")));
            for(int i = 0; i < sort_queries(dataMap.getString("lista")).size(); i++)
            {
                zapis.println(sort_queries(dataMap.getString("lista")).get(i));
            }
            zapis.close();
        }
    }

    public boolean isCorrectquerry(String querry)
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

    public List sort_queries(String lista)
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
