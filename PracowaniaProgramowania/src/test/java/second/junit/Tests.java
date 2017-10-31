package second.junit;

import org.junit.Before;
import org.junit.Test;
import third.quartz.JobWithMap;
import third.quartz.JobMapSchedulerSql;

import static org.junit.Assert.*;

public class Tests {

    JobWithMap c;
    JobMapSchedulerSql d;

    @Before
    public void setUp(){
        c = new JobWithMap();
        d = new JobMapSchedulerSql();
    }
    @Test
    public void projektSqlTest1()
    {
        Boolean p = d.isCorrectquery("select * from tabela order by ASC");
        assertTrue(p == true);
    }
    @Test
    public void projektSqlTest2()
    {
        Boolean p = d.isCorrectquery("select * from tabela order ASC");
        assertTrue(p == false);
    }
    @Test
    public void projektTest1()
    {
        Boolean p = c.getBreak(13,45);
        assertTrue(p == false);
    }
    @Test
    public void projektTest2()
    {
        Boolean p = c.getBreak(13,30);
        assertTrue(p == true);
    }

}