package com.solvd.laba.hospitalProject.exceptions;

public class PharmacyInvalidEnventoryNumberException extends Exception {

    public PharmacyInvalidEnventoryNumberException(String message) {
        super("Inventory Exception: " + message);
    }
}
