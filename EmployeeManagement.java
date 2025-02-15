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
        return String.format("Emp ID: %d | Name: %-10s | Dept: %-10s | Salary: %.2f", empId, name, department, salary);
    }
}

class EmployeeManagement {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static int lastEmpId = 100;  
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1️⃣ Add Employee");
            System.out.println("2️⃣ Delete Employee");
            System.out.println("3️⃣ Update Employee");
            System.out.println("4️⃣ Display All Employees");
            System.out.println("5️⃣ Exit");
            System.out.print("🔹 Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline
            
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
                    System.out.println("✅ Exiting Employee Management System. Have a great day!");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }

    public static void addEmployee() {
        System.out.print("👤 Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("🏢 Enter Department: ");
        String department = sc.nextLine();
        System.out.print("💰 Enter Salary: ");
        double salary = sc.nextDouble();
        
        lastEmpId++;  
        Employee emp = new Employee(lastEmpId, name, department, salary);
        employees.add(emp);
        System.out.println("✅ Employee Added! Assigned Emp ID: " + lastEmpId);
    }

    public static void deleteEmployee() {
        System.out.print("❌ Enter Emp ID to Delete: ");
        int empId = sc.nextInt();
        Employee emp = findEmployeeById(empId);
        
        if (emp != null) {
            employees.remove(emp);
            System.out.println("✅ Employee Deleted Successfully!");
        } else {
            System.out.println("❌ Employee Not Found!");
        }
    }

    public static void updateEmployee() {
        System.out.print("🔄 Enter Emp ID to Update: ");
        int empId = sc.nextInt();
        sc.nextLine();  // Consume newline
        
        Employee emp = findEmployeeById(empId);
        if (emp != null) {
            System.out.print("👤 Enter New Name: ");
            String name = sc.nextLine();
            System.out.print("🏢 Enter New Department: ");
            String department = sc.nextLine();
            System.out.print("💰 Enter New Salary: ");
            double salary = sc.nextDouble();
            
            emp.setName(name);
            emp.setDepartment(department);
            emp.setSalary(salary);
            System.out.println("✅ Employee Updated Successfully!");
        } else {
            System.out.println("❌ Employee Not Found!");
        }
    }

    public static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("📭 No Employees Found!");
        } else {
            System.out.println("\n===== Employee List =====");
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    public static Employee findEmployeeById(int empId) {
        for (Employee e : employees) {
            if (e.getEmpId() == empId) {
                return e;
            }
        }
        return null;
    }
}
