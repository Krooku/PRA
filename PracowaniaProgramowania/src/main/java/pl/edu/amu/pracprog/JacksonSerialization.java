package pl.edu.amu.pracprog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.Employee;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import static pl.edu.amu.pracprog.JacksonSerialization.deserializeDemo;

public class JacksonSerialization {

    final static Logger logger = Logger.getLogger(JacksonSerialization.class);

    public static void serializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Set mapper to pretty-print
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);;

        //Create objects to serialize
        ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
        Employee employee = objectsCreator.getEmp();

        //Serialize to file and string
        mapper.writeValue(new File("result." + fileSuffix), employee);
        String jsonString = mapper.writeValueAsString(employee);
        logger.info("Printing serialized original object " + fileSuffix);
        System.out.println(jsonString);

        //Deserialize from file
        Employee deserializedEmployee = mapper.readValue(
                new File("result." + fileSuffix), Employee.class);

        //Give a rise
        deserializedEmployee.setAvgpp(deserializedEmployee.getAvgpp() * 2);

        //Serialize back
        mapper.writeValue(new File("result-modified." + fileSuffix), deserializedEmployee);
        String modifiedJsonString = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing serialized modified object " + fileSuffix);
        System.out.println(modifiedJsonString);

        //Serialize generic List
        List<Employee> employees = objectsCreator.getEmployees();
        String employeesListSerialized = mapper.writeValueAsString(employees);
        logger.info("Printing serialized employees list " + fileSuffix);
        System.out.println(employeesListSerialized);
    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Deserialized employee object from employees.* file in resources
        InputStream employeeIs = JacksonSerialization.class.getClassLoader().
                getResourceAsStream("employee." + fileSuffix);
        Employee deserializedEmployee = mapper.readValue(employeeIs, Employee.class);
        deserializedEmployee.setAvgpp(new Random().nextInt(100000));
        String modifiedSerialzied = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing modified re-serialized employee to" + fileSuffix);
        System.out.println(modifiedSerialzied);

        // Deserialize employees list
        InputStream employeesIs = JacksonSerialization.class.getClassLoader().getResourceAsStream("employees." + fileSuffix);

        List<Employee> employees = mapper.readValue(employeesIs, List.class);

        System.out.println("Number of deserialized employees: " + employees.size());
    }

    public static void main(String[] args) throws IOException {


        ObjectMapper jsonMapper = new ObjectMapper();
        serializeDemo(jsonMapper, "json");
        deserializeDemo(jsonMapper, "json");

        ObjectMapper xmlMapper = new XmlMapper();
        serializeDemo(xmlMapper, "xml");
        deserializeDemo(xmlMapper, "xml");

    }
}


