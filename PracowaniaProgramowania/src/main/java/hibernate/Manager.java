package hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.model.Address;
import hibernate.model.Employee;
import hibernate.queries.Queries;
import pl.edu.amu.pracprog.ModelObjectsCreator;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import hibernate.xml_json;

import static hibernate.xml_json.deserializeDemo;
import static hibernate.xml_json.serializeDemo;

class Manager {

    public static void main(String[] args) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        serializeDemo(jsonMapper, "json", false);
        deserializeDemo(jsonMapper, "json");

        ObjectMapper xmlMapper = new XmlMapper();
        serializeDemo(xmlMapper, "xml", false);
        deserializeDemo(xmlMapper, "xml");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            //rozpocznij transakcje
            entityManager.getTransaction().begin();

            ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
            List<Employee> employees = objectsCreator.getEmployees();

            for(int i = 0; i < employees.size(); i++)
            {
                entityManager.persist(employees.get(i));
            }
            Query query = entityManager.createQuery("SELECT k FROM Employee k");
            List<Employee> resultquery = query.getResultList();

            //odstep od logow
            System.out.println("\n\n\n\n\n\n\n");

            chooseBestPlayer(entityManager);

            entityManager.getTransaction().commit();

            entityManager.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }

    }

    // read a page of empleyees and change first one to Nowak
    static void chooseBestPlayer(EntityManager entityManager) {

        //Query query = entityManager.createQuery("SELECT k FROM Employee k");
        List<Employee> employees = new Queries(entityManager).bestPlayer();
        System.out.println("Najlepszy gracz to: " + employees.get(0));

    }
}
