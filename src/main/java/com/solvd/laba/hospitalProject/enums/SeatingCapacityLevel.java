package com.solvd.laba.hospitalProject.enums;

public enum SeatingCapacityLevel {
    SMALL(1, "Small (1-10 people)"),
    MEDIUM(2, "Medium (10-40 people)"),
    LARGE(3, "Large (over 40 people)");

    private final int levelCode;
    private final String levelDescription;

    SeatingCapacityLevel(int levelCode, String levelDescription) {
        this.levelCode = levelCode;
        this.levelDescription = levelDescription;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public String getLevelDescription() {
        return levelDescription;
    }
}

