package hibernate.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;
import java.util.Date;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="id", scope=Employee.class)
@Entity
@Table(name = "EMPLOYEE", uniqueConstraints = {@UniqueConstraint(columnNames = {"nick"})})
public class Employee {

    @Column(name = "nick", nullable = false, unique = true)
    private String nick;

    @Column(name = "lane")
    private String lane;

    @Column(name = "team")
    private String team;

    @Column(name = "avqpp")
    private int avgpp;

    @Column(name = "joined", nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private DateTime joined;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="Address_ID", referencedColumnName = "id")
    //Address address;

    @ManyToMany(mappedBy = "main_players", cascade = CascadeType.ALL)
    private List<Employee> main_players = new ArrayList<Employee>();

    //@ManyToMany(cascade = CascadeType.ALL)
    //private List<Employee>  main_players = new ArrayList<>();

    @ManyToMany(mappedBy = "reserve_players", cascade = CascadeType.ALL)
    private List<Employee> reserve_players = new ArrayList<Employee>();

    //@ManyToMany(cascade = CascadeType.ALL)
    //private List<Employee>  reserve_players = new ArrayList<>();

    public Employee() {}

    public List<Employee> getMainPlayers() {
        return main_players;
    }

    public List<Employee> getReservePlayers() {
        return reserve_players;
    }

    public void setMainPlayers(List<Employee> main_players) {
        this.main_players = main_players;
    }

    public void setReservePlayers(List<Employee> reserve) {
        this.reserve_players = reserve;
    }

    /*public List<Employee> getMain() {
        return main_players;
    }

    public void setMain(List<Employee> main_players) {
        this.main_players = main_players;
    }

    public List<Employee> getReserve() {
        return reserve_players;
    }

    public void setReserve(List<Employee> reserve_players) {
        this.reserve_players = reserve_players;
    }*/

    public String getNick() {
        return nick;
    }
    public void setNick( String nick ) {
        this.nick = nick;
    }

    public String getLane() {
        return lane;
    }

    public void setLane( String lane ) {
        this.lane = lane;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam( String team ) {
        this.team = team;
    }

    public int getAvgpp() {
        return avgpp;
    }

    public void setAvgpp( int avgpp ) {
        this.avgpp = avgpp;
    }

    public DateTime getJoinedData() {
        return joined;
    }

    public void setJoinedData(DateTime joined) {
        this.joined = joined;
    }

    /*public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/


}