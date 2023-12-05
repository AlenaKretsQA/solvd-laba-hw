package com.solvd.laba.hospitalProject.exceptions;

public class LaboratoryInvalidEquipmentNameException extends Exception{
    public LaboratoryInvalidEquipmentNameException(String message) {
        super("Not this equipment found" + message);
    }
}