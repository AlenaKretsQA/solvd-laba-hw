package com.solvd.laba.hospitalProject.departments;

import com.solvd.laba.hospitalProject.Hospital;
import com.solvd.laba.hospitalProject.people.patients.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MedicalDepartment extends Department {

    public static final Logger LOGGER = LogManager.getLogger(Hospital.class);
    private Set<Patient> patients;
    private boolean hasEmergencyServices;

    public MedicalDepartment(String name, LocalDate establishedDate, boolean hasEmergencyServices) {
        super(name, establishedDate);
        this.hasEmergencyServices = hasEmergencyServices;
        this.patients = new HashSet<>();
        LOGGER.info("Created new Medical Department");
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public boolean removePatient(Patient patient) {
        this.patients.remove(patient);
        return false;
    }

    public boolean isHasEmergencyServices() {
        return hasEmergencyServices;
    }

    public void setHasEmergencyServices(boolean hasEmergencyServices) {
        this.hasEmergencyServices = hasEmergencyServices;
    }

    @Override
    public void performGeneralDuties() {
        LOGGER.info("Medical department is providing healthcare services.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MedicalDepartment that = (MedicalDepartment) o;
        return hasEmergencyServices == that.hasEmergencyServices && Objects.equals(patients, that.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patients, hasEmergencyServices);
    }

    @Override
    public String toString() {
        return "MedicalDepartment{" +
                "name='" + name + '\'' +
                ", establishedDate=" + establishedDate +
                ", hasEmergencyServices=" + hasEmergencyServices +
                ", patients=" + patients +
                ", employees=" + employees +
                '}';
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }

    @Override
    public void displayInfo() {
        LOGGER.info("Displaying information about the medical department.");
    }

    @Override
    public void showDetails() {
        LOGGER.info("Showing details of the medical department.");
    }

    @Override
    public void printInfo() {
        LOGGER.info("Printing information about the medical department.");
    }
}
