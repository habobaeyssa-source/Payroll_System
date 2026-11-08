public class Bonus {
    private double bonusRate;
    private double amountRate;



//    public Bonus(double bonusRate, double amountRate) {
//        if (bonusRate < 0 || amountRate < 0) {
//            throw new IllegalArgumentException("Rates cannot be negative");
//        }
//        if(workRecord.getHoursWorked()>208 && workRecord.getHoursWorked()<224 ){
//        this.bonusRate = bonusRate;
//        this.amountRate = amountRate;}
//    }


    public void calculateBonusRate(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours and rate cannot be negative");
        }
        if (hoursWorked > 208 && hoursWorked < 224) {
            this.bonusRate = 0.1;
        } else if (hoursWorked >= 224) {
            this.bonusRate = 0.25;
        } else {
            this.bonusRate = 0;
        }
    }

    public double calculateAmountRate(double hoursWorked, double hourlyRate) {
        if (hoursWorked < 0 || hourlyRate < 0) {
            throw new IllegalArgumentException("Hours and rate cannot be negative");
        }
        this.amountRate = hoursWorked * hourlyRate * bonusRate;
        return amountRate;
    }


    public double getBonusRate() { return bonusRate; }
    public void setBonusRate(double bonusRate) { this.bonusRate = bonusRate; }

    public double getAmountRate() { return amountRate; }
    public void setAmountRate(double amountRate) { this.amountRate = amountRate; }
}