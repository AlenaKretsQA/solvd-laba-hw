package com.solvd.laba.hospitalProject.enums;

public enum SalaryType {
    HOURLY("Hourly Rate"),
    MONTHLY("Monthly Salary"),
    ANNUAL("Annual Salary");

    private final String description;

    SalaryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public double calculateAnnualSalary(double baseSalary) {
        switch (this) {
            case HOURLY:
                // 40 hours per week and 52 weeks per year
                return baseSalary * 40 * 52;
            case MONTHLY:
                return baseSalary * 12;
            case ANNUAL:
                return baseSalary;
            default:
                throw new UnsupportedOperationException("Unsupported SalaryType: " + this);
        }
    }

    {
        System.out.println("Initializing SalaryType: " + this);
    }
}
