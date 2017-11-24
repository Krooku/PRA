package pl.edu.amu.pracprog;

import hibernate.model.Address;
import hibernate.model.Employee;
import org.joda.time.DateTime;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper class which provides methods to create instances of hibernate.model classes
 */
public class ModelObjectsCreator {

    Employee emp, emp1, emp2, emp3, emp4, emp5, emp6;
    Address address;

    List<Employee> employees;

    public Employee getEmp() {
        return emp;
    }

    public Address getAddress() {
        return address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void init(){
/*
        //add address
        Address address = new Address();
        address.setCity("poznan");
        address.setStreet("poznanska");
        address.setNr("1");
        address.setPostcode("99090");

        emp.setAddress(address);
        emp3.setAddress(address);
        emp2.setAddress(address);*/

        //SKT
        emp = new Employee();
        emp.setNick("kkOma");
        emp.setLane("coach");
        emp.setTeam("SKT T1");
        emp.setAvgpp(0);
        emp.setJoinedData(new DateTime());

        emp1 = new Employee();
        emp1.setNick("Faker");
        emp1.setLane("MID");
        emp1.setTeam("SKT T1");
        emp1.setAvgpp(147);
        emp1.setJoinedData(new DateTime());

        emp2 = new Employee();
        emp2.setNick("Huni");
        emp2.setLane("TOP");
        emp2.setTeam("SKT T1");
        emp2.setAvgpp(155);
        emp2.setJoinedData(new DateTime());

        emp3 = new Employee();
        emp3.setNick("Peanut");
        emp3.setLane("JNG");
        emp3.setTeam("SKT T1");
        emp3.setAvgpp(133);
        emp3.setJoinedData(new DateTime());

        emp4 = new Employee();
        emp4.setNick("Bang");
        emp4.setLane("ADC");
        emp4.setTeam("SKT T1");
        emp4.setAvgpp(150);
        emp4.setJoinedData(new DateTime());

        emp5 = new Employee();
        emp5.setNick("Wolf");
        emp5.setLane("SUP");
        emp5.setTeam("SKT T1");
        emp5.setAvgpp(109);
        emp5.setJoinedData(new DateTime());

        emp6 = new Employee();
        emp6.setNick("Blank");
        emp6.setLane("jungle");
        emp6.setTeam("SKT T1");
        emp6.setAvgpp(131);
        emp6.setJoinedData(new DateTime());

        emp.getMainPlayers().add(emp1);
        emp.getMainPlayers().add(emp2);
        emp.getMainPlayers().add(emp3);
        emp.getMainPlayers().add(emp4);
        emp.getMainPlayers().add(emp5);
        emp.getReservePlayers().add(emp6);



        employees = new ArrayList<Employee>();
        employees.add(emp);
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);


    }

    public ModelObjectsCreator(){
        init();
    }


}
