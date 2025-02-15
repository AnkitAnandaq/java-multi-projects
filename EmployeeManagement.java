import java.util.*;

class Employee {
    private int empId;
    private String name;
    private String department;
    private double salary;

    public Employee(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp ID: " + empId + ", Name: " + name + ", Department: " + department + ", Salary: " + salary;
    }
}

class EmployeeManagement {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static int lastEmpId = 100;  
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Employee\n2. Delete Employee\n3. Update Employee\n4. Display All Employees\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    displayEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void addEmployee() {
        System.out.print("Enter Employee Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String department = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        lastEmpId++;
        Employee emp = new Employee(lastEmpId, name, department, salary);
        employees.add(emp);
        System.out.println("Employee Added! Emp ID: " + lastEmpId);
    }

    public static void deleteEmployee() {
        System.out.print("Enter Emp ID to Delete: ");
        int empId = sc.nextInt();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getEmpId() == empId) {
                employees.remove(e);
                System.out.println("Employee Deleted Successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee Not Found!");
        }
    }

    public static void updateEmployee() {
        System.out.print("Enter Emp ID to Update: ");
        int empId = sc.nextInt();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getEmpId() == empId) {
                System.out.print("Enter New Name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Enter New Department: ");
                String department = sc.nextLine();
                System.out.print("Enter New Salary: ");
                double salary = sc.nextDouble();
                e.setName(name);
                e.setDepartment(department);
                e.setSalary(salary);
                System.out.println("Employee Updated Successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee Not Found!");
        }
    }

    public static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No Employees Found!");
        } else {
            System.out.println("\nEmployee List:");
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }
}
