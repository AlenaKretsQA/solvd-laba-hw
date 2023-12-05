package com.solvd.laba.hospitalProject.enums;

public enum DiseaseType {

    FLU(1.2),
    CANCER(2.5),
    DIABETES(1.8),
    HEART_DISEASE(2.0),
    PNEUMONIA(1.5),
    ALLERGIES(1.1);

    private final double chargeMultiplier;

    DiseaseType(double chargeMultiplier) {
        this.chargeMultiplier = chargeMultiplier;
    }

    public double getChargeMultiplier() {
        return chargeMultiplier;
    }
}
