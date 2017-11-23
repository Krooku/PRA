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

    Employee emp, emp1, emp2, emp3, emp4, emp5, emp12;
    Employee emp6, emp7, emp8, emp9, emp10, emp11;
    Address address;

    List<Employee> employees;

    public Employee getEmp() {
        return emp;
    }

    public Employee getEmp1() {
        return emp1;
    }

    public Employee getEmp2() {
        return emp2;
    }

    public Employee getEmp3() {
        return emp3;
    }

    public Employee getEmp4() {
        return emp4;
    }

    public Employee getEmp5() {
        return emp5;
    }

    public Employee getEmp6() {
        return emp6;
    }

    public Address getAddress() {
        return address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void init(){
        /*emp = new Employee();
        emp.setFirstName("ELDO");
        emp.setLastName("Polak" + new Random().nextInt());
        emp.setSalary(100);
        emp.setBirth(new DateTime(DateTime.now()));
        emp.setPesel(Math.abs(new Random().nextInt()));

        emp3 = new Employee();
        emp3.setFirstName("ELDO2");
        emp3.setLastName("Polak" + new Random().nextInt());
        emp3.setSalary(100);
        emp3.setBirth(new DateTime(DateTime.now()));
        emp3.setPesel(Math.abs(new Random().nextInt()));

        emp2 = new Employee();
        emp2.setFirstName("ZIOMBO");
        emp2.setLastName("Polak" + new Random().nextInt());
        emp2.setSalary(100);
        emp2.setPesel(Math.abs(new Random().nextInt()));



        //add address
        Address address = new Address();
        address.setCity("poznan");
        address.setStreet("poznanska");
        address.setNr("1");
        address.setPostcode("99090");

        emp.setAddress(address);
        emp3.setAddress(address);
        emp2.setAddress(address);


        emp.getSubworkers().add(emp2);
        emp2.getSubworkers().add(emp);

        employees = new ArrayList<Employee>();
        employees.add(emp);
        employees.add(emp3);
        employees.add(emp2);*/

        //SKT
        emp12 = new Employee();
        emp12.setNick("Blank");
        emp12.setLane("jungle");
        emp12.setTeam("SKT T1");
        emp12.setAvgpp(131);
        emp12.setJoinedData(new DateTime());

        emp = new Employee();
        emp.setNick("kkOma");
        emp.setLane("coach");
        emp.setTeam("SKT T1");
        emp.setAvgpp(0);
        emp.setJoinedData(new DateTime("2010-10-10"));

        emp1 = new Employee();
        emp1.setNick("Faker");
        emp1.setLane("MID");
        emp1.setTeam("SKT T1");
        emp1.setAvgpp(147);
        emp1.setJoinedData(new DateTime("2010-10-10"));

        emp2 = new Employee();
        emp2.setNick("Huni");
        emp2.setLane("TOP");
        emp2.setTeam("SKT T1");
        emp2.setAvgpp(155);
        emp2.setJoinedData(new DateTime("2010-10-10"));

        emp3 = new Employee();
        emp3.setNick("Peanut");
        emp3.setLane("JNG");
        emp3.setTeam("SKT T1");
        emp3.setAvgpp(133);
        emp3.setJoinedData(new DateTime("2010-10-10"));

        emp4 = new Employee();
        emp4.setNick("Bang");
        emp4.setLane("ADC");
        emp4.setTeam("SKT T1");
        emp4.setAvgpp(150);
        emp4.setJoinedData(new DateTime("2010-10-10"));

        emp5 = new Employee();
        emp5.setNick("Wolf");
        emp5.setLane("SUP");
        emp5.setTeam("SKT T1");
        emp5.setAvgpp(109);
        emp5.setJoinedData(new DateTime("2010-10-10"));

        emp.getMainPlayers().add(emp1);
        emp.getMainPlayers().add(emp2);
        emp.getMainPlayers().add(emp3);
        emp.getMainPlayers().add(emp4);
        emp.getMainPlayers().add(emp5);
        emp.getReservePlayers().add(emp12);

        //SSG
        emp6 = new Employee();
        emp6.setNick("Edgar");
        emp6.setLane("coach");
        emp6.setTeam("SSG");
        emp6.setAvgpp(0);
        emp6.setJoinedData(new DateTime("2010-10-10"));

        emp7 = new Employee();
        emp7.setNick("Crown");
        emp7.setLane("MID");
        emp7.setTeam("SSG");
        emp7.setAvgpp(171);
        emp7.setJoinedData(new DateTime("2010-10-10"));

        emp8 = new Employee();
        emp8.setNick("CuVee");
        emp8.setLane("TOP");
        emp8.setTeam("SSG");
        emp8.setAvgpp(177);
        emp8.setJoinedData(new DateTime("2010-10-10"));

        emp9 = new Employee();
        emp9.setNick("Ambition");
        emp9.setLane("JNG");
        emp9.setTeam("SSG");
        emp9.setAvgpp(148);
        emp9.setJoinedData(new DateTime("2010-10-10"));

        emp10 = new Employee();
        emp10.setNick("Ruler");
        emp10.setLane("ADC");
        emp10.setTeam("SSG");
        emp10.setAvgpp(205);
        emp10.setJoinedData(new DateTime("2010-10-10"));

        emp11 = new Employee();
        emp11.setNick("CoreJJ");
        emp11.setLane("SUP");
        emp11.setTeam("SSG");
        emp11.setAvgpp(141);
        emp11.setJoinedData(new DateTime("2010-10-10"));

        emp6.getMainPlayers().add(emp7);
        emp6.getMainPlayers().add(emp8);
        emp6.getMainPlayers().add(emp9);
        emp6.getMainPlayers().add(emp10);
        emp6.getMainPlayers().add(emp11);

        /*emp1.getPlayers().add(emp);
        emp2.getPlayers().add(emp);
        emp3.getPlayers().add(emp);
        emp4.getPlayers().add(emp);
        emp5.getPlayers().add(emp);*/


        employees = new ArrayList<Employee>();
        employees.add(emp);
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);
        employees.add(emp7);
        employees.add(emp8);
        employees.add(emp9);
        employees.add(emp10);
        employees.add(emp11);
        employees.add(emp12);


    }

    public ModelObjectsCreator(){
        init();
    }


}
