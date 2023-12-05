package com.solvd.laba.hospitalProject.exceptions;

public class PatientAlreadyExistException extends Exception {
    public PatientAlreadyExistException() {
        super("Patient already exist.");
    }
}
