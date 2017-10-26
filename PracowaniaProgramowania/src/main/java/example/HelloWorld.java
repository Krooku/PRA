package example;

import org.apache.log4j.Logger;
import third.quartz.JobMapScheduler;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

import java.io.IOException;

public class HelloWorld {

    final static Logger logger = Logger.getLogger(HelloWorld.class);

    public static void main(String [ ] args) throws InterruptedException, IOException {
        logger.info("PRZYROST I");

        Scanner in = new Scanner(System.in);
        Map<Integer, String> Sql = new HashMap<Integer, String>();

        JobMapScheduler a = null;
        while(true)
        {
            a.main(new String[]{});

            // Read the char
            System.out.print("Podaj nr zadania: ");
            int nr = in.nextInt();
            System.out.print(nr + "\n");
            in.nextLine();
            System.out.print("Podaj treść zapytania SQL: ");
            String sql = in.nextLine();
            System.out.print(sql + "\n");

            if(isCorrectquerry(sql))
                System.out.print("Dobre zapytanie\n");
            else
                System.out.print("Błędne zapytanie\n");
            //podaj jeszcze raz zapytanie
            //else
            //map dodaj nr, sql

        }
    }
    public static boolean isCorrectquerry(String querry)
    {
        //int len = querry.length();
        String wynik[] = null;
        wynik = querry.split(" ");
        int len = wynik.length;

        int prev = 0;

        if(!wynik[0].equalsIgnoreCase("select"))
            return false;
        System.out.print(wynik[0] + "\n");
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