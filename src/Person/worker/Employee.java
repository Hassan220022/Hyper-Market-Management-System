package Person.worker;

import java.util.ArrayList;
import java.util.HashMap;

public class Employee {

    private static int id = 0;
    protected String username;
    protected String password;
    protected static ArrayList<Employee> employees = new ArrayList<>();
    protected static HashMap<String, Integer> employeeMap = new HashMap<>();

    public Employee(String username, String password) {
        id++;
        this.username = username;
        this.password = password;
        employees.add(this);
        employeeMap.put(username, id);
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // public
    public static ArrayList<Employee> getEmployeeList() {
        return employees;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    // return employee using id
    public static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

}
