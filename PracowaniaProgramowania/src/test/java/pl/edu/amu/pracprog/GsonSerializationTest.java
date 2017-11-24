package pl.edu.amu.pracprog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import hibernate.xml_json;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static hibernate.xml_json.deserializeDemo;
import static hibernate.xml_json.serializeDemo;
import static org.junit.Assert.*;

public class GsonSerializationTest {

    ObjectMapper jsonMapper = new ObjectMapper();

    ObjectMapper xmlMapper = new XmlMapper();


    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void test1() throws IOException {
        assertTrue(serializeDemo(jsonMapper, "json", true));
    }
    @Test
    public void test2() throws IOException {
        assertTrue(serializeDemo(xmlMapper, "xml", true));
    }
    @Test
    public void test3() throws IOException {
        assertTrue(deserializeDemo(jsonMapper, "json"));
    }
    @Test
    public void test4() throws IOException {
        assertTrue(deserializeDemo(xmlMapper, "xml"));
    }


}