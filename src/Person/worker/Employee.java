package Person.worker;

import java.util.*;

public class Employee {

    private static int id = 0;
    protected String username;
    protected String password;
    protected HashSet<String> type = new HashSet<>();
    protected static ArrayList<Employee> employees = new ArrayList<>();
    protected static HashMap<Employee, Integer> employeeMap = new HashMap<>();

    public Employee(String username, String password, String type) {
        id++;
        this.username = username;
        this.password = password;
        this.type.add(type);
        employees.add(this);
        employeeMap.put(this, id);
    }

    protected static void addEmployee_To_ArrayList(Employee employee) {
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

    protected void setId(int id) {
        Employee.id = id;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected static void setEmployees(ArrayList<Employee> employees) {
        Employee.employees = employees;
    }

    protected static void setEmployeeMap(HashMap<Employee, Integer> employeeMap) {
        Employee.employeeMap = employeeMap;
    }

    protected void setType(String type) {
        this.type.add(type);
    }

}
