package hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.Employee;
import pl.edu.amu.pracprog.JacksonSerialization;
import pl.edu.amu.pracprog.ModelObjectsCreator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class xml_json {
    public static boolean serializeDemo(ObjectMapper mapper, String fileSuffix, boolean test) throws IOException {
        try {
            //Set mapper to pretty-print
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.registerModule(new JodaModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            //Create objects to serialize
            ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
            Employee employee = objectsCreator.getEmp();

            //Serialize to file and string
            if(!test)
                mapper.writeValue(new File("result." + fileSuffix), employee);
            String jsonString = mapper.writeValueAsString(employee);
            System.out.println(jsonString);

            //Deserialize from file
            Employee deserializedEmployee = null;
            if(!test)
                deserializedEmployee = mapper.readValue(new File("result." + fileSuffix), Employee.class);


            //Serialize generic List
            List<Employee> employees = objectsCreator.getEmployees();
            String employeesListSerialized = mapper.writeValueAsString(employees);
            System.out.println(employeesListSerialized);

            return true;
        }
        catch(Throwable ex)
        {
            System.err.println(ex);
            return false;
        }
    }

    public static boolean deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Deserialized employee object from employees.* file in resources
        try {
            InputStream employeeIs = JacksonSerialization.class.getClassLoader().getResourceAsStream("employee." + fileSuffix);

            Employee deserializedEmployee = mapper.readValue(employeeIs, Employee.class);

            String modifiedSerialzied = mapper.writeValueAsString(deserializedEmployee);

            System.out.println(modifiedSerialzied);

            // Deserialize employees list
            InputStream employeesIs = JacksonSerialization.class.getClassLoader().getResourceAsStream("employees." + fileSuffix);

            List<Employee> employees = mapper.readValue(employeesIs, List.class);

            System.out.println("Number of deserialized employees: " + employees.size());
            return true;
        }
        catch(Throwable ex)
        {
            System.err.println(ex);
            return false;
        }
    }

}
