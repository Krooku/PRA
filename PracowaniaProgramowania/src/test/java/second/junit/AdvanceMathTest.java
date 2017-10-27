package second.junit;

import example.HelloWorld;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import third.quartz.JobWithMap;
import third.quartz.JobWithMapSql;

import static org.junit.Assert.*;

public class AdvanceMathTest {

    AdvanceMath math;
    JobWithMap c;
    JobWithMapSql d;
    final static Logger logger = Logger.getLogger(AdvanceMath.class);

    HelloWorld hello;

    @Before
    public void setUp(){
        logger.info("setUp");
        math = new AdvanceMath();
        c = new JobWithMap();
        d = new JobWithMapSql();
    }
    @Test
    public void projektSqlTest1()
    {
        Boolean p = d.isCorrectquerry("select * from tabela order by ASC");
        assertTrue(p == true);
    }
    @Test
    public void projektSqlTest2()
    {
        Boolean p = d.isCorrectquerry("select * from tabela order ASC");
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