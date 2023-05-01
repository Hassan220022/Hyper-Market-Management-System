package Person.worker;

import java.util.*;

public class Admin extends Employee {

    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static HashMap<String, Integer> adminMap = new HashMap<>();

    public Admin(String username, String password) {
        super(username, password, "Admin");
        admins.add(this);
        adminMap.put(username, getId());
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static ArrayList<Admin> getAdminList() {
        return admins;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Admin changeAdminUsername(Admin admin, String OldUsername, String newUsername) {
        if (!admin.getUsername().equals(OldUsername)) {
            System.out.println("Old username is wrong");
            return null;
        }
        admin.setUsername(newUsername);
        return admin;
    }

    public Admin changeAdminPassword(Admin admin, String OldPassword, String newPassword) {
        if (!admin.getPassword().equals(OldPassword)) {
            System.out.println("Old password is wrong");
            return null;
        }
        admin.setPassword(newPassword);
        return admin;
    }

    private static class EmployeeList {

        private static Employee[] employeeList = new Employee[0];

        public static Employee[] getEmployeeList() {
            return employeeList;
        }
    }

    public static Employee getEmployee(int id) {
        for (Employee employee : EmployeeList.getEmployeeList()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public static Employee removeEmployee(int id) {
        if (!employeeMap.containsValue(id)) {
            EmployeeList.getEmployeeList()[id] = null;
            return employeeMap.get(id);
        }
        return null;
    }

    // set unique id for a spacific employee
    public static void setEmployeeId(Employee employee, int id) {
        employee.setId(id);
        employeeMap.replace(employee, id);
    }

    // set unique password for a spacific employee
    public static void setEmployeePassword(Employee employee, String password) {
        employee.setPassword(password);
    }

    // set unique type for a spacific employee
    public static void setEmployeeType(Employee employee, String type) {
        employee.setType(type);
    }

    // delete employee from the list and form the map and from the database
    public static void deleteEmployee(Employee employee) {
        employees.remove(employee);
        employeeMap.remove(employee);
    }

    // add employee to the list and to the map and to the database
    public static void addEmployee(Employee employee) {
        employees.add(employee);
        employeeMap.put(employee, employee.getId());
    }
    // update
}
