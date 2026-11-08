package org.example;

public class WorkRecord {
    //attribute
    private int employeeId;
    private int recordId;
    private int month;
    private int year;
    private double hoursWorked;

    //constructor

    public WorkRecord(int recordId,int employeeId,int month,int year,double hoursWorked){
        setRecordId(recordId);
        setYear(year);
        setMonth(month);
        setHoursWorked(hoursWorked);
        setEmployeeId(employeeId);
    }
    //methods

    public int getRecordId() {return recordId;}
    public int getEmployeeId() {return employeeId;}
    public int getMonth() {return month;}
    public int getYear() {return year;}
    public double getHoursWorked() {return hoursWorked;}


    private void setEmployeeId(int employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Invalid Employee ID");
        }
        this.employeeId = employeeId;
    }

    private void setYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be more than zero");
        }
        this.year = year;
    }

    private void setRecordId(int recordId) {
        if (recordId < 1000 || recordId > 9999) {
            throw new IllegalArgumentException("Record ID must be 4 digits");
        }
        this.recordId = recordId;
    }

    private void setMonth(int month) {
        if(month<1||month>12){throw new IllegalArgumentException("Month must be between 1 and 12");}
        this.month = month;
    }

    public void setHoursWorked(double hoursWorked){
        if (hoursWorked<0) {
            throw new IllegalArgumentException("hourWorked cannot be negative");
        }

        if (hoursWorked>(30*24)){
            throw new IllegalArgumentException("exceeds the maximum possible hours");
        }
        else{this.hoursWorked=hoursWorked;}
    }
}
