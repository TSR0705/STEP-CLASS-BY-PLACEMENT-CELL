public class EmployeePayrollSystem {
    public static void main(String[] args) {
        // Create Employees
        Employee e1 = new Employee("E001", "Rahul Sharma", "IT", 50000, "Full-Time");
        Employee e2 = new Employee("E002", "Ananya Singh", "HR", 300, 40, "Part-Time"); // hourlyRate, hours
        Employee e3 = new Employee("E003", "Amit Verma", "Finance", 60000, "Contract");

        // Demonstrate method overloading
        e1.generatePaySlip(5000); // full-time with bonus
        e2.generatePaySlip();     // part-time with hourly rate × hours
        e3.generatePaySlip();     // contract with fixed amount

        // Display company report
        System.out.println("\n--- Company Payroll Report ---");
        Employee.displayTotalEmployees();
    }
}

// ================== Employee Class ==================
class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;

    private double hourlyRate;
    private int hoursWorked;

    private static int totalEmployees = 0;

    // Constructor for Full-time Employee
    public Employee(String empId, String empName, String department, double baseSalary, String empType) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = empType;
        totalEmployees++;
    }

    // Constructor for Part-time Employee
    public Employee(String empId, String empName, String department, double hourlyRate, int hoursWorked, String empType) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.empType = empType;
        totalEmployees++;
    }

    // Constructor for Contract Employee (fixed salary)
    public Employee(String empId, String empName, String department, String empType, double fixedAmount) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = fixedAmount;
        this.empType = empType;
        totalEmployees++;
    }

    // ================= Salary Calculation (Overloaded) =================
    // Full-time (baseSalary + bonus)
    public double calculateSalary(double bonus) {
        return baseSalary + bonus;
    }

    // Part-time (hourlyRate × hoursWorked)
    public double calculateSalary() {
        if (empType.equalsIgnoreCase("Part-Time")) {
            return hourlyRate * hoursWorked;
        }
        return baseSalary; // Default for others
    }

    // Contract Employee (fixed salary)
    public double calculateSalary(boolean contract) {
        return baseSalary;
    }

    // ================= Tax Calculation (Overloaded) =================
    public double calculateTax(double salary) {
        if (empType.equalsIgnoreCase("Full-Time")) {
            return salary * 0.2; // 20% tax
        } else if (empType.equalsIgnoreCase("Part-Time")) {
            return salary * 0.1; // 10% tax
        } else {
            return salary * 0.15; // Contract = 15% tax
        }
    }

    // ================== Pay Slip ==================
    public void generatePaySlip(double bonus) {
        double salary = calculateSalary(bonus);
        double tax = calculateTax(salary);
        double netPay = salary - tax;

        System.out.println("\n--- Pay Slip ---");
        displayEmployeeInfo();
        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax Deducted: " + tax);
        System.out.println("Net Pay: " + netPay);
    }

    public void generatePaySlip() {
        double salary;
        if (empType.equalsIgnoreCase("Part-Time")) {
            salary = calculateSalary(); // hourly
        } else if (empType.equalsIgnoreCase("Contract")) {
            salary = calculateSalary(true); // fixed
        } else {
            salary = baseSalary; // fallback
        }
        double tax = calculateTax(salary);
        double netPay = salary - tax;

        System.out.println("\n--- Pay Slip ---");
        displayEmployeeInfo();
        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax Deducted: " + tax);
        System.out.println("Net Pay: " + netPay);
    }

    // ================== Display Info ==================
    public void displayEmployeeInfo() {
        System.out.println("ID: " + empId + ", Name: " + empName +
                ", Dept: " + department + ", Type: " + empType);
    }

    // ================== Static Report ==================
    public static void displayTotalEmployees() {
        System.out.println("Total Employees in Company: " + totalEmployees);
    }
}
