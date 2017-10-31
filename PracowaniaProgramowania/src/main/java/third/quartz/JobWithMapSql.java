package third.quartz;
import org.quartz.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@PersistJobDataAfterExecution
public class JobWithMapSql implements Job
{
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            PrintWriter zapis = null;
            try {
                zapis = new PrintWriter(dataMap.getString("path"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < sort_queries(dataMap.getString("lista")).size(); i++)
            {
                zapis.println(sort_queries(dataMap.getString("lista")).get(i));
            }
            zapis.close();
        }
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
