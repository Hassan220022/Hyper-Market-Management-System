package Empoyee;

import java.util.ArrayList;
import java.util.List;

class Admin extends Employee {
    private List<Employee> employees;

    public Admin(String name, String username, String password) {
        super("Admin", name, username, password);
        this.employees = new ArrayList<Employee>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void deleteEmployee(int employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            employees.remove(employee);
        }
    }

    public void updateEmployee(Employee employee) {
        Employee existingEmployee = findEmployeeById(employee.getId());
        if (existingEmployee != null) {
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setType(employee.getType());
        }
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> searchEmployees(String keyword) {
        List<Employee> searchResults = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getId() == Integer.parseInt(keyword)) {
                searchResults.add(employee);
            }
        }
        return searchResults;
    }
}

// Rest of the code remains the same
