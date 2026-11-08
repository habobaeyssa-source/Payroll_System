import java.util.ArrayList;
import java.util.List;

public class PayrollManager {
    private List<Employee> employees;
    private List<Payroll> payrolls;
    private List<WorkRecord> workRecords;


    // Dependencies used when creating payrolls
    private TaxCalculator taxCalculator;
    private Bonus bonus; // optional, can be null

    private int nextPayrollId = 1;
    private int nextReportId = 1;

    public PayrollManager(TaxCalculator taxCalculator, Bonus bonus) {
        this.employees = new ArrayList<>();
        this.payrolls = new ArrayList<>();
        this.workRecords = new ArrayList<>();
        this.taxCalculator = taxCalculator;
        this.bonus = bonus;
    }

    // ---------------- Employees ----------------

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (findEmployee(employee.getEmployeeId()) != null) {
            throw new IllegalArgumentException(
                    "Employee already exists: " + employee.getEmployeeId());
        }
        employees.add(employee);
    }

    public void updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employee.getEmployeeId()) {
                employees.set(i, employee);
                return;
            }
        }
        throw new IllegalArgumentException(
                "Employee not found: " + employee.getEmployeeId());
    }

    public void removeEmployee(int employeeId) {
        Employee e = findEmployee(employeeId);
        if (e == null) {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }
        employees.remove(e);
    }

    // returns null if not found
    public Employee findEmployee(int employeeId) {
        for (Employee e : employees) {
            if (e.getEmployeeId() == employeeId) {
                return e;
            }
        }
        return null;
    }

    // ---------------- Work records ----------------

    public void addWorkRecord(int employeeId, WorkRecord record) {

        if (findEmployee(employeeId) == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        if (record == null) {
            throw new IllegalArgumentException("WorkRecord cannot be null");
        }

        if (record.getEmployeeId() != employeeId) {
            throw new IllegalArgumentException("Employee ID mismatch");
        }

        if (findWorkRecord(employeeId, record.getMonth(), record.getYear()) != null) {
            throw new IllegalArgumentException("WorkRecord already exists");
        }

        workRecords.add(record);
    }


    private WorkRecord findWorkRecord(int employeeId, int month, int year) {

        for (WorkRecord r : workRecords) {

            if (r.getEmployeeId() == employeeId &&
                    r.getMonth() == month &&
                    r.getYear() == year) {

                return r;
            }
        }

        return null;
    }


    // ---------------- Payroll ----------------

    public Payroll processPayroll(int employeeId, int month, int year) {
        return processPayroll(employeeId, month, year, new ArrayList<>());
    }

    public Payroll processPayroll(int employeeId, int month, int year,
                                  List<Deduction> deductions) {
        Employee employee = findEmployee(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }
        WorkRecord record = findWorkRecord(employeeId, month, year);
        if (record == null) {
            throw new IllegalStateException("No work record for employee "
                    + employeeId + " in " + month + "/" + year);
        }
        if (findPayroll(employeeId, month, year) != null) {
            throw new IllegalStateException("Payroll already processed for "
                    + month + "/" + year);
        }

        Payroll payroll = new Payroll(nextPayrollId++, employee, record, taxCalculator, bonus, employee.getAssignedPosition());
        for (Deduction d : deductions) {
            payroll.addDeduction(d);
        }
        payroll.calculateNetSalary();
        payrolls.add(payroll);
        return payroll;
    }

    private Payroll findPayroll(int employeeId, int month, int year) {
        for (Payroll p : payrolls) {
            if (p.getEmployee().getEmployeeId() == employeeId
                    && p.getMonth() == month && p.getYear() == year) {
                return p;
            }
        }
        return null;
    }
}

// ---------------- Reports ----------------

//    public FinancialReport generateMonthlyReport();
//
//    public FinancialReport generateAnnualReport();
//
//    public TaxSummary generateTaxSummary();
