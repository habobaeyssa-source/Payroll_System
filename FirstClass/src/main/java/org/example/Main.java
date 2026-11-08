
package org.example;
public class Main {
    static void main() {
        WorkRecord wr = new WorkRecord(1554,01,5,2027,250);
        JobPosition jp=new JobPosition(1,"Manager",60,800);
        Bonus b=new Bonus();
        TaxCalculator t=new TaxCalculator();

        Deduction d1 = new Deduction(1, "Insurance", 300,"late");
        Deduction d2 = new Deduction(2, "Late", 150,"late");
        Deduction d3 = new Deduction(3, "Absence", 200,"late");



        Employee e=new Employee("menesy","mahmoud2006" , "programming" ,true,01,"Mahmoud Menesy",21,jp);
        Payroll p=new Payroll(1,e,wr,t,b,jp);

        p.addDeduction(d1);
        p.addDeduction(d2);
        p.addDeduction(d3);

        t.setTaxRate(jp.getTitle());
        double salary = p.calculateNetSalary();

        double net = p.calculateNetSalary();

        System.out.println("Gross Salary = " + p.getGrossSalary());
        System.out.println("Bonus = " + b.getAmountRate());
        System.out.println("Tax Rate = " + t.getTaxRate());
        System.out.println("Tax Amount = " + p.getTaxAmount());
        System.out.println("Net Salary = " + net);

        System.out.println(p.generatePayslip());
    }
}
