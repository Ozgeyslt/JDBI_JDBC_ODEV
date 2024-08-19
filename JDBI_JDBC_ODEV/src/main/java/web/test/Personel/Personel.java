package web.test.Personel;

import web.test.Connection.JDBCConnecion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Personel {

    private Long id;
    private String firstName;
    private String lastName;

    private int salary;

    public Personel( Long id,String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


}
