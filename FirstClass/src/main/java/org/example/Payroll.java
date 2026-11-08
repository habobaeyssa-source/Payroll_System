
import java.util.ArrayList;
import java.util.List;

public class Payroll {

    private int payrollId;
    private Employee employee;
    private WorkRecord workRecord;
    private List<Deduction> deductions;

    private double grossSalary;
    private double taxAmount;
    private double totalDeductions;
    private double netSalary;

    private int month;
    private int year;

    private TaxCalculator taxCalculator;
    private Bonus bonus;
    private JobPosition jp;

    public Payroll(int payrollId, Employee employee,
                   WorkRecord workRecord,
                   TaxCalculator taxCalculator,
                   Bonus bonus,
                   JobPosition jp) {

        this.payrollId = payrollId;
        this.employee = employee;
        this.workRecord = workRecord;
        this.taxCalculator = taxCalculator;
        this.bonus = bonus;
        this.jp = jp;

        this.month = workRecord.getMonth();
        this.year = workRecord.getYear();

        this.deductions = new ArrayList<>();
    }

    public void addDeduction(Deduction deduction) {
        if (deduction == null) {
            throw new IllegalArgumentException(
                    "Deduction cannot be null"
            );
        }

        deductions.add(deduction);
    }

    public double calculateGrossSalary() {

        double salary = jp.getBaseSalary()
                + (workRecord.getHoursWorked()
                * jp.getHourlyRate())
                + jp.getAllowances();

        if (bonus != null) {
            bonus.calculateBonusRate(
                    workRecord.getHoursWorked()
            );

            salary += bonus.calculateAmountRate(
                    workRecord.getHoursWorked(),
                    jp.getHourlyRate()
            );
        }

        grossSalary = salary;
        return grossSalary;
    }

    public double calculateTax() {

        taxAmount = taxCalculator.getTaxAmount(workRecord , jp);

        return taxAmount;
    }

    public double calculateTotalDeductions() {

        double total = 0;

        for (Deduction d : deductions) {
            total += d.getAmount();
        }

        totalDeductions = total;

        return totalDeductions;
    }

    public double calculateNetSalary() {

        calculateGrossSalary();
        calculateTax();
        calculateTotalDeductions();

        netSalary = grossSalary
                - taxAmount
                - totalDeductions;

        return netSalary;
    }

    public String generatePayslip() {

        StringBuilder sb = new StringBuilder();

        sb.append("========== PAYSLIP ==========\n");
        sb.append("Payroll ID : ").append(payrollId).append("\n");
        sb.append("Employee   : ").append(employee.getName()).append("\n");
        sb.append("Period     : ").append(month)
                .append("/").append(year).append("\n");

        sb.append("-----------------------------\n");

        sb.append(String.format(
                "Gross Salary    : ", grossSalary
        ));

        sb.append(String.format(
                "Tax : ", taxAmount
        ));

        for (Deduction d : deductions) {
            System.out.println("  "+d.getType() + d.getAmount());


        }

        sb.append(String.format(
                "Total Deductions: ",
                totalDeductions
        ));

        System.out.println("--------------------------");
        sb.append(String.format(
                "Net Salary : ", netSalary
        ));


        System.out.println("--------------------------");


        return sb.toString();
    }

    public int getPayrollId() { return payrollId; }
    public Employee getEmployee() { return employee; }
    public WorkRecord getWorkRecord() { return workRecord; }
    public List<Deduction> getDeductions() { return deductions; }

    public int getMonth() { return month; }
    public int getYear() { return year; }

    public double getGrossSalary() { return grossSalary; }
    public double getTaxAmount() { return taxAmount; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getNetSalary() { return netSalary; }
}
