package com.solvd.laba.hospitalProject.exceptions;

public class LaboratoryInvalidQuantityEquipmentException extends Exception{
    public LaboratoryInvalidQuantityEquipmentException(String message) {
        super("Not this equipment " + message);
    }
}
