public class TaxCalculator {
    private double taxRate;
    private double taxAmount;

//    public TaxCalculator(double taxRate) {
//        setTaxRate(taxRate);
//    }

    public double getTaxRate() { return taxRate; }

    public void setTaxRate(String titleJob) {
        if (titleJob.equals("Manager")) {
            this.taxRate = 0.05;
        } else if (titleJob.equals("Employee")) {
            this.taxRate = 0.1;

        }else{
            throw new IllegalArgumentException("Tax rate for Manager and employee only");
        }
    }

    public double getTaxAmount(WorkRecord wr , JobPosition jp) {
        if (wr.getHoursWorked() < 0) {
            throw new IllegalArgumentException("Gross salary cannot be negative");
        }
        this.taxAmount=wr.getHoursWorked() * jp.getHourlyRate() * taxRate;
        return taxAmount;
    }
}