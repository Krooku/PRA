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
        logger.info("Odpalam setUpa");
        math = new AdvanceMath();
        c = new JobWithMap();
        d = new JobWithMapSql();
    }
    @Test
    public void projektSqlTest()
    {
        Boolean p = d.isCorrectquerry("select * from tabela order by ASC");
        assertTrue(p == true);
    }
    @Test
    public void projektTest()
    {
        Boolean p = c.getBreak(13,45);
        assertTrue(p == false);
    }
    @Test
    public void additionTest() {
        Integer a = math.addition(1,4);
        assertTrue(a==5);
    }

    @Test
    public void additionTestString() {
        long a = math.addition("1",4);
        Assert.assertEquals(5L, a);
    }

    @Test(expected = Exception.class)
    public void additionTestString2() {
        int a = math.addition("a1",4);
    }

}