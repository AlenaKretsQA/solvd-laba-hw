package com.solvd.laba.hospitalProject.enums;

public enum LaboratoryType {
    CLINICAL("Opened during clinical hours"),
    HEMATOLOGY("Specializes on blood-related tests"),
    MICROBIOLOGY("Deals with microbial studies");

    private final String description;

    LaboratoryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void printLabInfo() {
        System.out.println("Lab Type: " + this.name());
        System.out.println("Description: " + description);
    }

    static {
        System.out.println("Initializing Laboratory Types...");
    }
}
