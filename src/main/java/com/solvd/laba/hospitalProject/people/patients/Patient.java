package com.solvd.laba.hospitalProject.people.patients;

import com.solvd.laba.hospitalProject.enums.DiseaseType;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;
import com.solvd.laba.hospitalProject.interfaces.Payable;
import com.solvd.laba.hospitalProject.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public final class Patient extends Person implements Payable {

    public static final Logger LOGGER = LogManager.getLogger(Patient.class);
    private DiseaseType disease;
    private static int patientCount;

    public Patient(String firstName, String lastName, String phoneNumber,DiseaseType disease) throws InvalidPersonException {

        super(firstName, lastName, phoneNumber);
        this.disease = disease;

        patientCount ++;
        LOGGER.info("Create a new patient # " + patientCount + " from " + patientCount);
    }

    public DiseaseType getDisease() {
        return disease;
    }

    public void setDisease(DiseaseType disease) {
        this.disease = disease;
    }

    public static int getPatientCount() {
        return patientCount;
    }

    public static void setPatientCount(int patientCount) {
        Patient.patientCount = patientCount;
    }

    public Optional<String> getHealthStatus() {
        if (disease != null) {
            String healthStatus = "Patient is managing " + disease + " condition.";
            return Optional.of(healthStatus);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "disease='" + disease + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public Double showPaymentAmount() {
        double basePayment = 150.0;
        double diseaseChargeMultiplier = 1.2;

        // Assuming a patient with a disease incurs an additional charge
        double additionalChargeMultiplier = disease != null ? disease.getChargeMultiplier() : 1.0;

        return basePayment * additionalChargeMultiplier;
    }

    @Override
    public void displayPaymentHistory() {
        LOGGER.info("No payment history available for patient: " + this.toString());
    }
}
