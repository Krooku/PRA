package hibernate;

import hibernate.model.Address;
import hibernate.model.Employee;
import hibernate.queries.Queries;
import pl.edu.amu.pracprog.ModelObjectsCreator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Manager {

    public static void main(String[] args) {

        System.out.println("Start");

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

            //System.out.println(resultquery);
            chooseBestPlayer(entityManager);
            //changeFirstGuyToNowak(entityManager);

            //entityManager.getTransaction().commit();

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