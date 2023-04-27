package Person.worker;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends Employee {

    private String type;
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static HashMap<String, Integer> adminMap = new HashMap<>();

    public Admin(String username, String password) {
        super(username, password);
        this.type = "Admin";
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
}
