package com.solvd.laba.hospitalProject.exceptions;

public class PharmacyInvalidEnventoryNameException extends Exception {

    public PharmacyInvalidEnventoryNameException(String message) {
        super("Inventory Exception: " + message);
    }
}
