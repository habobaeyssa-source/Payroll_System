public class Deduction {
    private int deductionId;
    private String type;
    private double amount;
    private String description;

    public Deduction(int deductionId, String type, double amount, String description) {
        this.deductionId = deductionId;
        this.type = type;
        setAmount(amount);
        this.description = description;
    }

    public int getDeductionId() { return deductionId; }

    public String getType() { return type; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deduction amount cannot be negative");
        }
        this.amount = amount;
    }

    public String getDescription() { return description; }
}