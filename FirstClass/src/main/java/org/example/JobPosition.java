package org.example;

public class JobPosition {
    private int positionId;
    private String title;
    public static double baseSalary=5000;
    private double hourlyRate;
    private double allowances;

    public JobPosition(int positionId, String title, double hourlyRate, double allowances) {
        this.positionId = positionId;
        this.title = title;
        this.hourlyRate = hourlyRate;
        this.allowances = allowances;
    }

    // Getters
    public int getPositionId() { return positionId; }
    public String getTitle() { return title; }
    public double getBaseSalary() { return baseSalary; }
    public double getHourlyRate() { return hourlyRate; }
    public double getAllowances() { return allowances; }

    // Setters
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public void setAllowances(double allowances) { this.allowances = allowances; }
}