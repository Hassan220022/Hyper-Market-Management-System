package Empoyee;

import java.util.*;

class Employee {
    protected int id;
    protected String password;
    protected String type;
    protected String name;
    protected String username;
    protected static ArrayList<Employee> employees = new ArrayList<Employee>();

    public Employee(String type, String name, String username, String password) {
        this.id = ID.generateID(type);
        this.username = username;
        this.password = password;
        this.type = type;
        this.name = name;
        employees.add(this);
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String usename) {
        this.username = usename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
